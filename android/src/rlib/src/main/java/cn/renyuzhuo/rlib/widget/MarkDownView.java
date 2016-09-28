package cn.renyuzhuo.rlib.widget;

import android.content.Context;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.util.Log;
import android.webkit.URLUtil;
import android.webkit.WebView;

import org.markdownj.MarkdownProcessor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by renyuzhuo on 16-9-28.
 * <br/>
 * Email: rwebrtc@gmail.com
 * <br/>
 * Reference: https://github.com/falnatsheh/MarkdownView
 */
public class MarkDownView extends WebView {
    public MarkDownView(Context context) {
        super(context);
    }

    public MarkDownView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MarkDownView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private static final String TAG = "MarkdownView";

    /**
     * Loads the given Markdown text to the view as rich formatted HTML. The
     * HTML output will be styled based on the given CSS file.
     *
     * @param txt        - input in markdown format
     * @param cssFileUrl - a URL to css File. If the file located in the project assets
     *                   folder then the URL should start with "file:///android_asset/"
     */
    public void loadMarkdown(String txt, String cssFileUrl) {
        loadMarkdownToView(txt, cssFileUrl);
    }

    /**
     * Loads the given Markdown text to the view as rich formatted HTML.
     *
     * @param txt - input in Markdown format
     */
    public void loadMarkdown(String txt) {
        loadMarkdown(txt, null);
    }

    /**
     * Loads the given Markdown file to the view as rich formatted HTML. The HTML
     * output will be styled based on the given CSS file.
     *
     * @param url        - a URL to the Markdown file. If the file located in the
     *                   project assets folder then the URL should start with
     *                   "file:///android_asset/"
     * @param cssFileUrl - a URL to css File. If the file located in the project assets
     *                   folder then the URL should start with "file:///android_asset/"
     */
    public void loadMarkdownFile(String url, String cssFileUrl) {
        new LoadMarkdownUrlTask().execute(url, cssFileUrl);
    }

    public void loadMarkdownFile(String url) {
        loadMarkdownFile(url, null);
    }

    private String readFileFromAsset(String fileName) {
        try {
            InputStream input = getContext().getAssets().open(fileName);
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(input));
                StringBuilder content = new StringBuilder(input.available());
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    content.append(line);
                    content.append(System.getProperty("line.separator"));
                }
                return content.toString();
            } finally {
                input.close();
            }
        } catch (Exception ex) {
            Log.d(TAG, "Error while reading file from assets", ex);
            return null;
        }
    }

    private class LoadMarkdownUrlTask extends
            AsyncTask<String, Integer, String> {
        private String cssFileUrl;

        protected String doInBackground(String... params) {
            try {
                String markdown = "";
                String url = params[0];
                this.cssFileUrl = params[1];
                if (URLUtil.isNetworkUrl(url)) {
                    markdown = HttpHelper.get(url).getResponseMessage();
                } else if (URLUtil.isAssetUrl(url)) {
                    markdown = readFileFromAsset(url.substring("file:///android_asset/".length(), url.length()));
                } else {
                    throw new IllegalArgumentException("The URL string provided is not a network URL or Asset URL.");
                }

                return markdown;
            } catch (Exception ex) {
                Log.d(TAG, "Error Loading Markdown File.", ex);
                return null;
            }
        }

        protected void onProgressUpdate(Integer... progress) {
            // no-op
        }

        protected void onPostExecute(String result) {
            if (result != null) {
                loadMarkdownToView(result, cssFileUrl);
            } else {
                loadUrl("about:blank");
            }
        }
    }

    private void loadMarkdownToView(String txt, String cssFileUrl) {
        MarkdownProcessor m = new MarkdownProcessor();
        String html = m.markdown(txt);
        if (cssFileUrl != null) {
            html = "<link rel='stylesheet' type='text/css' href='" + cssFileUrl + "' />" + html;
        }
        loadDataWithBaseURL("fake://", html, "text/html", "UTF-8", null);
    }

    static class HttpHelper {

        private static final String CHARSET_UTF8 = "UTF-8";
        public static final String CONTENT_TYPE_JSON = "json";
        public static final String CONTENT_TYPE_XML = "xml";

        // Timeout when reading from Input stream when a connection is established
        // to a resource
        private static final int DEFULT_READ_TIMEOUT = 5000;
        // Timeout for establishing a connection.
        private static final int DEFULT_CONNECT_TIMEOUT = 5000;

        static public Response get(String url, String query)
                throws MalformedURLException, IOException {
            return get(url, query, DEFULT_CONNECT_TIMEOUT, DEFULT_READ_TIMEOUT);
        }

        static public Response get(String url) throws MalformedURLException,
                IOException {
            return get(url, null, DEFULT_CONNECT_TIMEOUT, DEFULT_READ_TIMEOUT);
        }

        static public Response get(String url, String query, int connectTimeout,
                                   int readTimeout) throws MalformedURLException, IOException {
            String fullUrl = url;
            if (query != null && !query.equals("")) {
                fullUrl += "?" + query;
            }
            URLConnection connection = new URL(fullUrl).openConnection();
            connection.setReadTimeout(readTimeout);
            connection.setConnectTimeout(connectTimeout);
            connection.setRequestProperty("Accept-Charset", CHARSET_UTF8);
            return getResponse((HttpURLConnection) connection);
        }

        static public Response post(String url, String query, String contentType)
                throws MalformedURLException, IOException {
            return post(url, query, contentType, DEFULT_CONNECT_TIMEOUT,
                    DEFULT_READ_TIMEOUT);
        }

        static public Response post(String url, String query, String contentType,
                                    int connectTimeout, int readTimeout) throws MalformedURLException,
                IOException {
            URLConnection connection = new URL(url).openConnection();
            connection.setReadTimeout(readTimeout);
            connection.setConnectTimeout(connectTimeout);
            connection.setDoOutput(true); // Triggers POST.
            connection.setRequestProperty("Accept-Charset", CHARSET_UTF8);
            connection.setRequestProperty("Content-Type", "application/"
                    + contentType);
            OutputStream output = null;
            try {
                output = connection.getOutputStream();
                output.write(query.getBytes(CHARSET_UTF8));
            } finally {
                closeSilently(output);
            }
            return getResponse((HttpURLConnection) connection);
        }

        /*
         * Open the input stream to get responses from the server.
         */
        private static Response getResponse(HttpURLConnection connection)
                throws IOException {
            InputStream inputStream = connection.getInputStream();
            Response response = new Response();
            response.setHttpResponseCode(connection.getResponseCode());
            response.setHttpResponseHeader(connection.getHeaderFields().entrySet());
            response.setResponseMessage(getResponseMessage(inputStream, connection));
            response.setHttpResponseMessage(connection.getResponseMessage());
            return response;
        }

        /*
         * Get the HTTP response message from the server.
         */
        private static String getResponseMessage(InputStream inputStream,
                                                 HttpURLConnection connection) throws UnsupportedEncodingException,
                IOException {
            String responseMessage = null;
            StringBuffer sb = new StringBuffer();
            InputStream dis = connection.getInputStream();
            int chr;
            while ((chr = dis.read()) != -1) {
                sb.append((char) chr);
            }
            if (sb != null) {
                responseMessage = sb.toString();
            }
            return responseMessage;
        }

        /*
         * Close the connection, if the connection could not be closed (probably
         * because its already closed) ignore the error.
         */
        private static void closeSilently(OutputStream output) {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                }
            }
        }

        public static class Response {

            private Set<Map.Entry<String, List<String>>> httpResponseHeader;
            private int httpResponseCode;
            private String httpResponseMessage;
            private String serverResponseMessage;

            Response() {
            }

            Response(Set<Map.Entry<String, List<String>>> httpResponseHeader,
                     int httpResponseCode, String httpResponseMessage,
                     String responseMessage) {
                setHttpResponseHeader(httpResponseHeader);
                setHttpResponseCode(httpResponseCode);
                setHttpResponseMessage(httpResponseMessage);
                setResponseMessage(responseMessage);
            }

            public String getHttpResponseMessage() {
                return httpResponseMessage;
            }

            public void setHttpResponseMessage(String httpResponseMessage) {
                this.httpResponseMessage = httpResponseMessage;
            }

            public Set<Map.Entry<String, List<String>>> getHttpResponseHeader() {
                return httpResponseHeader;
            }

            public void setHttpResponseHeader(
                    Set<Map.Entry<String, List<String>>> httpResponseHeader) {
                this.httpResponseHeader = httpResponseHeader;
            }

            public int getHttpResponseCode() {
                return httpResponseCode;
            }

            public void setHttpResponseCode(int httpResponseCode) {
                this.httpResponseCode = httpResponseCode;
            }

            public String getResponseMessage() {
                return serverResponseMessage;
            }

            public void setResponseMessage(String responseMessage) {
                this.serverResponseMessage = responseMessage;
            }

            public String toString() {
                return "httpResponseCode = " + httpResponseCode + " , "
                        + "httpResponseMessage = " + httpResponseMessage + " , "
                        + "serverResponseMessage = " + serverResponseMessage;
            }

        }

    }
}

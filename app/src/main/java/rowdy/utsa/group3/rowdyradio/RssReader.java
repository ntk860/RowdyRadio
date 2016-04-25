package rowdy.utsa.group3.rowdyradio;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 */
public class RssReader {
    public volatile boolean parsingComplete = true;
    private String title;
    private String link;
    private String description;
    private String urlString;
    private XmlPullParserFactory xmlPullParserFactory;

    /**
     * @param urlString String representation of the RSS URL
     */
    public RssReader(String urlString) {
        title = "";
        link = "";
        description = "";
        this.urlString = urlString;
    }

    /**
     * @return the title of the news item
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return the external link to the news item
     */
    public String getLink() {
        return link;
    }

    /**
     * @return the description of the news item
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return a String representation of the RSS URL
     */
    public String getUrlString() {
        return urlString;
    }

    /**
     * @param urlString a String representation of the new RSS URL
     */
    public void setUrlString(String urlString) {
        this.urlString = urlString;
    }

    /**
     * Performs the main operation of retrieving the RSS feed XML
     */
    public void getXml() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(urlString);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setReadTimeout(10000);
                    connection.setConnectTimeout(5000);
                    connection.setRequestMethod("GET");
                    connection.setDoInput(true);

                    connection.connect();
                    InputStream stream = connection.getInputStream();

                    xmlPullParserFactory = XmlPullParserFactory.newInstance();
                    XmlPullParser parser = xmlPullParserFactory.newPullParser();

                    parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
                    parser.setInput(stream, null);

                    parseXml(parser);
                    stream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
    }

    private void parseXml(XmlPullParser parser) {
        int event;
        String text = "";

        try {
            event = parser.getEventType();
            while (event != XmlPullParser.END_DOCUMENT) {
                String name = parser.getName();
                switch (event) {
                    case XmlPullParser.START_TAG:
                        break;
                    case XmlPullParser.TEXT:
                        text = parser.getText();
                        break;
                    case XmlPullParser.END_TAG:
                        switch (name) {
                            case "title":
                                title = text;
                                break;
                            case "link":
                                link = text;
                                break;
                            case "description":
                                description = text;
                                break;
                        }
                        break;
                }
                event = parser.next();
            }
            parsingComplete = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package rowdy.utsa.group3.rowdyradio;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;


/**
 *
 */
@Parcel
public class StreamInfoPOJO {
    @SerializedName("icestats")
    private Stats icecastStats;

    public Stats getIcecastStats() {
        return icecastStats;
    }

    public void setIcecastStats(Stats icecastStats) {
        this.icecastStats = icecastStats;
    }

    private class Stats {
        @SerializedName("admin")
        private String admin;
        @SerializedName("host")
        private String host;
        @SerializedName("location")
        private String location;
        @SerializedName("server_id")
        private String serverId;
        @SerializedName("server_start")
        private String serverStart;
        @SerializedName("server_start_iso8601")
        private String serverStartISO;
        @SerializedName("source")
        private Source source;

        public String getAdmin() {
            return admin;
        }

        public void setAdmin(String admin) {
            this.admin = admin;
        }

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getServerId() {
            return serverId;
        }

        public void setServerId(String serverId) {
            this.serverId = serverId;
        }

        public String getServerStart() {
            return serverStart;
        }

        public void setServerStart(String serverStart) {
            this.serverStart = serverStart;
        }

        public String getServerStartISO() {
            return serverStartISO;
        }

        public void setServerStartISO(String serverStartISO) {
            this.serverStartISO = serverStartISO;
        }

        public Source getSource() {
            return source;
        }

        public void setSource(Source source) {
            this.source = source;
        }

        private class Source {
            @SerializedName("audio_info")
            private String audioInfo;
            @SerializedName("channels")
            private int channels;
            @SerializedName("genre")
            private String genre;
            @SerializedName("listener_peak")
            private int listenerPeak;
            @SerializedName("listeners")
            private int listeners;
            @SerializedName("listenurl")
            private String listenURL;
            @SerializedName("quality")
            private int quality;
            @SerializedName("samplerate")
            private int sampleRate;
            @SerializedName("server_description")
            private String serverDescription;
            @SerializedName("server_name")
            private String serverName;
            @SerializedName("server_type")
            private String serverType;
            @SerializedName("stream_start")
            private String streamStart;
            @SerializedName("stream_start_iso8601")
            private String streamStartISO;
            @SerializedName("title")
            private String title;
            @SerializedName("dummy")
            private String dummy;

            public String getAudioInfo() {
                return audioInfo;
            }

            public void setAudioInfo(String audioInfo) {
                this.audioInfo = audioInfo;
            }

            public int getChannels() {
                return channels;
            }

            public void setChannels(int channels) {
                this.channels = channels;
            }

            public String getGenre() {
                return genre;
            }

            public void setGenre(String genre) {
                this.genre = genre;
            }

            public int getListenerPeak() {
                return listenerPeak;
            }

            public void setListenerPeak(int listenerPeak) {
                this.listenerPeak = listenerPeak;
            }

            public int getListeners() {
                return listeners;
            }

            public void setListeners(int listeners) {
                this.listeners = listeners;
            }

            public String getListenURL() {
                return listenURL;
            }

            public void setListenURL(String listenURL) {
                this.listenURL = listenURL;
            }

            public int getQuality() {
                return quality;
            }

            public void setQuality(int quality) {
                this.quality = quality;
            }

            public int getSampleRate() {
                return sampleRate;
            }

            public void setSampleRate(int sampleRate) {
                this.sampleRate = sampleRate;
            }

            public String getServerDescription() {
                return serverDescription;
            }

            public void setServerDescription(String serverDescription) {
                this.serverDescription = serverDescription;
            }

            public String getServerName() {
                return serverName;
            }

            public void setServerName(String serverName) {
                this.serverName = serverName;
            }

            public String getServerType() {
                return serverType;
            }

            public void setServerType(String serverType) {
                this.serverType = serverType;
            }

            public String getStreamStart() {
                return streamStart;
            }

            public void setStreamStart(String streamStart) {
                this.streamStart = streamStart;
            }

            public String getStreamStartISO() {
                return streamStartISO;
            }

            public void setStreamStartISO(String streamStartISO) {
                this.streamStartISO = streamStartISO;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getDummy() {
                return dummy;
            }

            public void setDummy(String dummy) {
                this.dummy = dummy;
            }
        }
    }
}




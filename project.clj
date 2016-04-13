(defproject clospil "0.1.0-SNAPSHOT"
  :description "FIXME: write this!"
  :url "http://example.com/FIXME"

  :dependencies [[org.clojure/clojure "1.8.0"]
		 [com.vaadin/vaadin-server "7.6.4"]
                 [com.vaadin/vaadin-client-compiled "7.6.4"]
                 [com.vaadin/vaadin-themes "7.6.4"]
                 [javax.servlet/servlet-api "2.5"]]
  :aot [clospil.clospilui]
  :plugins [[lein-servlet "0.3.0"]]
  
  :servlet {:deps [[lein-servlet/adapter-jetty7 "0.3.0"]]
            :config {:engine :jetty
                     :host "localhost"
                     :port 3000}
            :webapps {"/"
                       {:web-xml "src/main/webapp/WEB-INF/web.xml"
                        :public "resources"}}})

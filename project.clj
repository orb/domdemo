(defproject domdemo "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [compojure "1.1.6"]
                 [org.clojure/clojurescript "0.0-2138"]
                 [prismatic/dommy "0.1.2"]
                 [jayq "2.5.0"]]

  :plugins [[lein-ring "0.8.10"]
            [lein-cljsbuild "0.2.10"]]

  :ring {:handler domdemo.handler/app
         :nrepl {:start? true}}
  :profiles {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                                  [ring-mock "0.1.5"]]}}

  :cljsbuild {:builds [{:source-paths ["src-cljs"]
                        :compiler {:output-to "resources/public/second.js"
                                   :optimizations :whitespace
                                   :pretty-print true}}]})

(ns antd-demo.views
  (:require [re-frame.core :as re-frame]
            [antd-demo.subs :as subs]
            cljsjs.antd
            cljsjs.moment))

(def antd js/antd)
(def m js/moment)

(defn home-panel []
  (let [name (re-frame/subscribe [::subs/name])]
    [:div (str "Hello from " @name ". This is the Home Page.")
     [:> antd.DatePicker {:defaultValue (m)}]
     [:div [:a {:href "#/about"} "go to About Page"]]]))


;; about

(defn about-panel []
  [:div "This is the About Page."
   [:div [:a {:href "#/"} "go to Home Page"]]])


;; main

(defn- panels [panel-name]
  (case panel-name
    :home-panel [home-panel]
    :about-panel [about-panel]
    [:div]))

(defn show-panel [panel-name]
  [panels panel-name])

(defn main-panel []
  (let [active-panel (re-frame/subscribe [::subs/active-panel])]
    [show-panel @active-panel]))

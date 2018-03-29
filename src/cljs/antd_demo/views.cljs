(ns antd-demo.views
  (:require [re-frame.core :as re-frame]
            [antd-demo.subs :as subs]
            cljsjs.antd
            cljsjs.moment))

(def antd js/antd)
(def m js/moment)

(defn date-picker-example []
  [:div {:style {:margin 24}}
   [:p {:style {:marginBottom 24}}
    (str "Current antd version " antd.version) [:br]
    "Example taken from https://codesandbox.io/s/jnw46698m3"]
   [:> antd.DatePicker {:defaultValue (m)}]])

(defn home-panel []
  (let [name (re-frame/subscribe [::subs/name])]
    [:div (str "Hello from " @name ". This is the Home Page.")
     [date-picker-example]
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

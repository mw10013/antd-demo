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

(defn layout-example []
  [:> antd.Layout
   [:> antd.Layout.Sider "Sider"]
   [:> antd.Layout
    [:> antd.Layout.Header "Header"]
    [:> antd.Layout.Content "Content"]
    [:> antd.Layout.Footer "Footer"]]])

(defn home-panel []
  (let [name (re-frame/subscribe [::subs/name])]
    [:div (str "Hello from " @name ". This is the Home Page.")
     [date-picker-example]
     [:div {:style {:padding 24}}
      [layout-example]]
     [:div [:a {:href "#/about"} "go to About Page"]]]))


;; about

;http://imghst.co/95/tLLy~YWL8D.jpg
;<img src="https://s1.gifyu.com/images/baby-boy-heart-background.jpg" alt="" width="615" height="615" data-load="full" style="">

(defn about-panel []
  [:div "This is the About Page."
   [:div {:style {#_:background-color #_"lightblue"
                  :background "lightblue url('http://imghst.co/95/tLLy~YWL8D.jpg') no-repeat right top"
                  :padding    24
                  :margin     12
                  :width      615
                  :height     615}} "Background Image Test"]
   [:div [:a {:href "#/"} "go to Home Page"]]])


;; main

(defn- panels [panel-name]
  (case panel-name
    :home-panel [home-panel]
    :about-panel [about-panel]
    [:div]))

(defn show-panel [panel-name]
  [panels panel-name])

;.logo {
;       width: 120px;
;       height: 31px;
;       background: rgba(255,255,255,.2);
;       margin: 16px 24px 16px 0;
;       float: left;
;       }

(defn layout []
  [:> antd.Layout
   [:> antd.Layout.Header
    [:div {:style {:float :left :padding-right 20}}
     [:a {:href "#" :style {:text-decoration :none}} "Antd Demo"]]
    [:> antd.Menu {:theme :dark :mode :horizontal :selectable false
                   :style {:line-height "64px"}}
     [:> antd.Menu.Item {:key :1}
      [:a {:href "#/about"}] "About"]
     [:> antd.Menu.Item {:key :2}
      [:> antd.Icon {:type :mail}]
      "Nav 2"]
     [:> antd.Menu.SubMenu {:title "Admin"}
      [:> antd.Menu.Item "Users"]
      [:> antd.Menu.Item "Groups"]]
     [:> antd.Menu.Item {:key :3} "Nav 3"]]]
   [:> antd.Layout.Content {:style {:margin 0 :padding 12 :border "1px solid black"}}
    [:div {:style {:background "#fff" :padding 12 :min-height 280 :border "1px solid black"}} "Content"]]
   [:> antd.Layout.Footer {:style {:text-align :center}} "Footer"]])

(defn main-panel []
  (let [active-panel (re-frame/subscribe [::subs/active-panel])]
    #_[show-panel @active-panel]
    [layout]))

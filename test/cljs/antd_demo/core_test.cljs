(ns antd-demo.core-test
  (:require [cljs.test :refer-macros [deftest testing is]]
            [antd-demo.core :as core]))

(deftest fake-test
  (testing "fake description"
    (is (= 1 2))))

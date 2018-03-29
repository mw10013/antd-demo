(ns antd-demo.runner
    (:require [doo.runner :refer-macros [doo-tests]]
              [antd-demo.core-test]))

(doo-tests 'antd-demo.core-test)

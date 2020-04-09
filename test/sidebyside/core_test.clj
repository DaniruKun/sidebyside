(ns sidebyside.core-test
  (:require [clojure.test :refer :all]
            [sidebyside.core :refer :all])
  (:require [fivetonine.collage.core :refer :all])
  (:require [fivetonine.collage.util :as util])
  (:import java.awt.image.BufferedImage))

(deftest test-square-crop
  (testing "Image is square cropped."
    (let [test-out-img (square-crop "doc/demo-l.png")
          w (.getWidth test-out-img)
          h (.getHeight test-out-img)]
      (is (= w h))
      )
    ))

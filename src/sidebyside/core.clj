(ns sidebyside.core
  (:gen-class)
  (:require [fivetonine.collage.core :refer :all])
  (:require [fivetonine.collage.util :as util])
  (:import java.awt.image.BufferedImage))


(defn square-crop
  "Return square crop of BufferedImage."
  [path]
  (let [img (util/load-image path)
        w (.getWidth img) h (.getHeight img)]
    (let [cropped (cond
                    (> w h) (do
                              (let [crop-size h
                                    crop-x (- (quot w 2) (quot crop-size 2))
                                    crop-y 0]
                                (crop img crop-x crop-y crop-size crop-size)))
                    (< w h) (do
                              (let [crop-size w
                                    crop-x 0
                                    crop-y (- (quot h 2) (quot crop-size 2))]
                                (crop img crop-x crop-y crop-size crop-size))))]
      cropped)))

(defn image-join
  "Join 2 images together and crop."
  [img-l img-r]
  (let [w 1200 h (quot w 2) center h
        new-img (BufferedImage. w h BufferedImage/TYPE_INT_RGB)
        img-a (with-image (square-crop img-l)
                          (resize :width center :height h))
        img-b (with-image (square-crop img-r)
                          (resize :width center :height h))]
    (paste new-img img-a 0 0 img-b center 0)))

(defn -main
  [& args]
  (let [left (first args)
        right (nth args 1)
        out-img (nth args 2)]
    (util/save (image-join left right) out-img)))

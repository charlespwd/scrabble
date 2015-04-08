(ns scrabble.core
  (:require [clojure.java.io :as io]))

(def dictionary "/usr/share/dict/words")

(def scrabble-dictionary
  "A map of sorted letters => array of words formed by those letters."
  (with-open [reader (io/reader dictionary)]
    (->> (for [line (line-seq reader)] line)
         (map (fn [line] {(sort line) [line]}))
         (apply merge-with concat))))

(defn search [word]
  (get scrabble-dictionary (sort word) nil))

#_(search "table")
#_(search "ablet")
#_(search "zzzzz")

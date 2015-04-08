(ns scrabble.core
  (:require [clojure.java.io :as io]))

(def dictionary "/usr/share/dict/words")

(def scrabble-dictionary
  "A map of sorted letters => list of words formed by those letters."
  (with-open [reader (io/reader dictionary)]
    (->> (line-seq reader)
         (map (fn [line] {(sort line) [line]}))
         (apply merge-with concat))))

(defn search [word]
  (get scrabble-dictionary (sort word) nil))

#_(search "table") ;; => ("batel" "blate" "bleat" "table")
#_(search "ablet") ;; => ("batel" "blate" "bleat" "table")
#_(search "zzzzz") ;; => nil

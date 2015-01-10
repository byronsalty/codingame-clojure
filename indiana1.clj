(ns Player
  (:gen-class)
  (:use [clojure.string :only [split trim]])
  (:use [clojure.set :only [intersection union]]))

; Auto-generated code below aims at helping you parse
; the standard input according to the problem statement.

(def tiles
    {0 (fn [x y p] [x y])
     1 (fn [x y p] [x (inc y)])
     2 (fn [x y p] [(+ x (if (= p "LEFT") 1 -1)) y])
     3 (fn [x y p] [x (inc y)])
     4 (fn [x y p] (if (= p "TOP") [(dec x) y] [x (inc y)]))
     5 (fn [x y p] (if (= p "TOP") [(inc x) y] [x (inc y)]))
     6 (fn [x y p] [(+ x (if (= p "LEFT") 1 -1)) y])
     7 (fn [x y p] [x (inc y)])
     8 (fn [x y p] [x (inc y)])
     9 (fn [x y p] [x (inc y)])
     10 (fn [x y p] [(dec x) y])
     11 (fn [x y p] [(inc x) y])
     12 (fn [x y p] [x (inc y)])
     13 (fn [x y p] [x (inc y)])
    })

(defn readn []
    (Integer. (read)))


(defn get-lines [W H]
    (loop [i (* W H)
           acc []]
        (if (= 0 i)
            (partition W acc)
            (recur (dec i) (conj acc (read))))))

(defn get-tile-num [lines x y]
    (let [row (nth lines y)
          cell (nth row x)]
          (binding [*out* *err*]
            (println "  finding cell. x/y: " x "/" y)
            (println "  row: " row)
            (println "  cell: " cell))
        cell))

(defn get-tile [tile-num]
    (tiles tile-num))


(defn -main [& args]
  (let [W (readn) H (readn) skip (read-line)
        lines (get-lines W H)
        exit (readn) s2 (read-line)]

          (binding [*out* *err*]
            (println "W/H: " W "/" H)
            (println "lines: " lines))

      (while true
        (let [XI (readn) YI (readn) POS (trim (read-line))
              tile-num (get-tile-num lines XI YI)
              tile (get-tile tile-num)
              [nextX nextY] (tile XI YI POS)]

          (binding [*out* *err*]
            (println "X, Y: " XI "," YI)
            (println "POS: " POS)
            (println "X', Y': " nextX "," nextY))

          ; One line containing the X Y coordinates of the room in which you believe Indy will be on the next turn.
          (println nextX nextY)))))

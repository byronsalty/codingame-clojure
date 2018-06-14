(ns Solution
  (:gen-class)
  (:use [clojure.string :only [split trim join]]))

; Auto-generated code below aims at helping you parse
; the standard input according to the problem statement.

(def move-sequence {:normal (vec "SENW")})
(def move-names
    {\S "SOUTH"
     \E "EAST"
     \N "NORTH"
     \W "WEST"
     \L "LOOP"})

(defn readn []
    (Integer. (read)))

(defn readn-line []
    (let [n (readn) _ (read-line)]
        n))

(defn add-char [map char x y]
    true)

(defn remove-char [map x y]
    true)

(defn find-char [map char]
    (loop [y 0
           curr map]
        (if (nil? map)
            [-1 -1]
            (let [ind (.indexOf (first curr) char)]
                (if (>= ind 0)
                    [ind y]
                    (recur (inc y) (rest curr)))))))

(defn char-at [map x y]
    (if (> y 0)
        (char-at (rest map x (dec y)))
        (nth (first map) x)))


(defn get-row-map [y]
    (let [row (vec (read-line))]
        (reduce #(assoc %1 [(first %2) y] (second %2)) {} (map-indexed vector row))))

(defn get-map [H W]
    (loop [y 0
           acc {}]
        (binding [*out* *err*]
            (println "y: " y))

        (if (= y H)
            acc
            (recur (inc y) (merge acc (get-row-map y))))))

(defn get-move [map mode]
    \S)

(defn update-map [map move]
    map)

(defn update-mode [map move]
    :normal)

(defn get-row [map y]
    (let [row-keys (filter #(= y %2) (keys map))
          sorted (sort-by first row-keys)]
        (loop [keys sorted
               acc []]
            (if (= 0 (count keys))
                acc
                (recur (rest keys) (conj acc (get map (first keys))))))))


(defn print-map [map H]
    (loop [y 0]
        (if (not (= y H))
            (do
                (binding [*out* *err*]
                    (println (join "" (get-row map y))))
                (recur (inc y))))))

(defn print-move [move]
    (println (get move-names move)))


(defn -main [& args]
    (let [L (readn) C (readn-line)
        orig-map (get-map L C)]

        (loop [map orig-map
               mode :normal]
            (let [move (get-move map mode)
                  bender (find-char map \@)]

            (binding [*out* *err*]
                (println "bender: " bender))

                (print-map map L)
                (print-move move)
                (recur (update-map map move) (update-mode map move))))))

(ns Player
  (:gen-class)
  (:use [clojure.string :only [split trim join]])
  (:use [clojure.set :only [intersection union]]))


(defrecord Avatar [chr x y dir me dead])

(defn readn []
    (Integer. (read)))
(defn readn-line []
    (let [x (readn) skip (read-line)]
        x))
(defn abs [n]
    (max (* -1 n) n))

(def move-names
    {\U ["UP" (fn [x y] [x (inc y)])]
     \R ["RIGHT" (fn [x y] [(inc x) y])]
     \D ["DOWN" (fn [x y] [x (dec y)])]
     \L ["LEFT" (fn [x y] [(dec x) y])]
     })

(defn add-char [map char x y]
    (loop [current-y 0
           rem map
           acc []]
        (if (= 0 (count rem))
            acc
            (let [row (if (= y current-y)
                          (assoc (first rem) x char)
                          (first rem))]
                (recur (inc current-y) (rest rem) (conj acc row))))))

(defn del-char [map x y]
    (add-char map \space x y))

(defn find-char [map char]
    (loop [y 0
           curr map]
        (if (nil? map)
            [-1 -1]
            (let [ind (.indexOf (first curr) char)]
                (if (>= ind 0)
                    [ind y]
                    (recur (inc y) (rest curr)))))))

(defn remove-chars [map char]
    (let [[x y] (find-char map char)]
        (if (not (= -1 x))
            (remove-chars (del-char map x y) char))))

(defn char-at [map x y]
    (if (> y 0)
        (char-at (rest map) x (dec y))
        (nth (first map) x)))

(defn get-map [H W]
    (loop [i H
           acc []]
        (if (= 0 i)
            acc
            (recur (dec i) (conj acc (vec (take W (repeat \space))))))))

(defn get-dir [x0 y0 x1 y1]
    (first (filter (fn [key] (let [func (second (get move-names key))
                            [nx ny] (func x0 y0)]
                           (and (= nx x1) (= ny y1)))) (keys move-names))))


(defn update-models []
    (let [N (readn) P (readn-line)]
        (loop [i 0
               acc []]
            (binding [*out* *err*]
                (println "i: " i))
        (if (= N i)
            acc
            (let [X0 (readn) Y0 (readn) X1 (readn) Y1 (readn-line)
                  me? (= i P)
                  dead? (= -1 X1)]
                (recur (inc i) (conj acc (Avatar. i X1 Y1 (get-dir X0 Y0 X1 Y1) me? dead?))))))))


(defn update-map [map models]
    (loop [new-map map
           rem models]
        (if (= 0 (count rem))
            new-map
            (let [{:keys [chr x y dead]} (first rem)
                  map2 (if dead (remove-chars new-map chr) (add-char new-map chr x y))]
                (recur map2 (rest rem))))))

(defn print-map [map]
    (if (> (count map) 0)
        (do
            (binding [*out* *err*]
                (println (join "" (first map))))
            (print-map (rest map)))))

(defn fake-input []
  (binding [*out* *in*]
    (println "1 0")
    (println "10 20 10 20")))

(defn -main [& args]
    (println "hello")
    (let [orig-map (get-map 20 30)]
        (loop [i 0
               map orig-map
               models (update-models)]


            (binding [*out* *err*]
                (println "players: " models))
            (print-map map)

            ; A single line with UP, DOWN, LEFT or RIGHT
            (println "LEFT")
            (if (< i 30)
              (recur (inc i)
                     (update-map map models)
                     (update-models))))))

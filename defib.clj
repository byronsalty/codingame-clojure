(ns Solution
  (:gen-class)
  (:use [clojure.string :only [trim split replace]]))

; Auto-generated code below aims at helping you parse
; the standard input according to the problem statement.

(defrecord Defib [id name address phone long lat])

(defn get-num [nstr]
    (read-string (replace nstr #"\," ".")))


(defn get-defibs [N]
    (loop [i (Integer. N)
           acc []]
        (if (= 0 i)
            acc
            (let [DEFIB (read-line)
                  [id n a p long lat] (split DEFIB #"\;")
                  fib (Defib. id n a p (get-num long) (get-num lat))]

     (binding [*out* *err*]
        (println "  i: " i)
       (println "   found: !" (.name fib) "!")
       (println "   first: " (first (.name fib))))

                (recur (dec i) (conj acc fib))))))

(defn sq [n]
    (* n n))

(defn dist [latA longA latB longB]
    (let [x (* (- longB longA) (Math/cos (/ (+ latA latB) 2)))
          y (- latB latA)]
        (* 6371 (Math/sqrt (+ (sq x) (sq y))))))


(defn -main [& args]
  (let [LON (get-num (read-line)) LAT (get-num (read-line)) N (read-line)
        defibs (get-defibs N)
        dfn (fn [d] (dist LAT LON (.lat d) (.long d)))
        dists (map dfn defibs)
        closest (apply min dists)
        ind (.indexOf dists closest)
        names (map #(.name %) defibs)]


    (binding [*out* *err*]
        (println "lon: " LON)
        (println "lat: " LAT)
        (println "dists: " dists)
        (println "closest: " closest)
        (println "ind: " ind)
        (println "names: " names ", cnt: " (count names))
        (println "nth: " (nth names 0)))

    ; Write answer to stdout
    (println (nth names ind))))

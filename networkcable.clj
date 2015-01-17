(ns Solution
  (:gen-class))

; Auto-generated code below aims at helping you parse
; the standard input according to the problem statement.


(defn abs [n]
    (max (* -1 n) n))

(defn readn []
    (Integer. (read)))

(defn readn-line []
    (let [n (readn)
          skip (read-line)]
        n))

(defn get-houses [num]
    (loop [i num
           acc []]
        (if (= 0 i)
            acc
            (recur (dec i) (conj acc [(readn) (readn-line)])))))

;(defn get-dimensions [houses]
;    (let [left (reduce min (map first houses))
;          right (reduce max (map first houses))
;          top (reduce max (map second houses))
;          bottom (reduce min (map second houses))]
;        [left right top bottom]))

(defn get-dist [Ys one-num]
    (reduce + (map (fn [y] (abs (- y one-num))) Ys)))

(defn splitter [vals split-num]
    (let [size-per (quot (count vals) split-num)
          splits (partition split-num (quot split-num 2) nil vals)]
        splits))

(defn get-bounds [Ys upper lower]
    (let [midder (quot (+ upper lower) 2)
          up (get-dist Ys upper)
          low (get-dist Ys lower)
          mid (get-dist Ys midder)]
            (binding [*out* *err*]
                (println "upper: " upper "=" up)
                (println "lower: " lower "=" low))
        (if (>= up low)
            [lower midder]
            [midder upper])))


(defn get-sub-split [best3 size]
    (let [a1 (- (second (first best3)) size)
          a2 (+ (second (first best3)) size)
          b1 (- (second (second best3)) size)
          b2 (+ (second (second best3)) size)
          c1 (- (second (last best3)) size)
          c2 (+ (second (last best3)) size)]
        [a1 a2 b1 b2 c1 c2]))


(defn get-min-bounds [Ys upper lower]
    (loop [up-point upper
           low-point lower]
        (let [[new-low new-up] (get-bounds Ys up-point low-point)]
            (binding [*out* *err*]
                (println "up: " up-point)
                (println "low: " low-point))

            (if (<= (- up-point low-point) 1)
                (min (get-dist Ys low-point) (get-dist Ys up-point))
                (recur new-up new-low)))))

(defn get-by-splits [N Ys lower upper split-num]
            (binding [*out* *err*]
                (println "N: " N))
    (if (or (<= (- upper lower) split-num) (> N 1000))

    ;(if (<= (- upper lower) split-num)
        (get-min-bounds Ys upper lower)
        (let [split-size (quot (- upper lower) split-num)
              indexes (map #(+ (* split-size %) lower) (range split-num))
              vals (map (fn [ind] [(get-dist Ys ind) ind]) indexes)
              sorted (sort-by first vals)
              best3 (take 3 sorted)
              [a1 a2 b1 b2 c1 c2] (get-sub-split best3 split-size)
              dist1 (get-by-splits N Ys a1 a2 split-num)
              dist2 (get-by-splits N Ys b1 b2 split-num)
              dist3 (get-by-splits N Ys c1 c2 split-num)]
            (min dist1 dist2 dist3))))

(defn get-min-fn [Ys num fun]
    (loop [i num
           i-1 num
           curr 100000000000]
        (let [dist (get-dist Ys i)]
            (binding [*out* *err*]
                (println "i: " i)
                (println "curr: " curr)
                (println "dist: " dist))

            (if (> dist curr)
                (get-min-bounds Ys (max i i-1) (min i i-1))
                (recur (fun i) i dist)))))


(defn get-min-dist [Ys avg]
    (let [down-min (get-min-fn Ys avg (fn [x] (- x 2000)))
          up-min (get-min-fn Ys avg (fn [x] (+ x 2000)))]

    (binding [*out* *err*]
        (println "avg: " avg)
        (println "down: " down-min)
        (println "up: " up-min))

        (min down-min up-min)))

(defn -main [& args]
  (let [N (readn-line)
        houses (get-houses N)
        Xs (map first houses)
        Ys (map second houses)
        left (reduce min Xs)
        right (reduce max Xs)
        top (reduce max Ys)
        bottom (reduce min Ys)
        sum (reduce + Ys)
        avg-quot (quot sum N)
        half-top (quot (+ top avg-quot) 2)
        half-bottom (quot (+ bottom avg-quot) 2)
        ;avg-high (if (< (rem sum N) 0) (dec avg-low) (inc avg-low))
        width (abs (- right left))
        ;dist1 (reduce + (map (fn [y] (abs (- y avg-low))) Ys))
        ;dist2 (reduce + (map (fn [y] (abs (- y avg-high))) Ys))
        ;total (+ width (get-min-dist Ys avg-quot))
        ;total (+ width (get-min-bounds Ys half-top half-bottom))
        ;total (+ width (get-min-bounds Ys top bottom))
        total (+ width (get-by-splits N Ys bottom top 100))
        ]

    (binding [*out* *err*]
        (println "N: " N)
        (println "l r t b: " left right top bottom)
        (println "width: " width)
        (println "avg-quot: " avg-quot)
        (println "Ys: " Ys)
        (println "sum: " sum)
        (println "houses: " houses))

    ; Write answer to stdout
    (println total)))

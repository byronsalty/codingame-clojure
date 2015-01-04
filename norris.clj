(ns Solution
  (:gen-class))

; Auto-generated code below aims at helping you parse
; the standard input according to the problem statement.

(defn pad-int [istr]
    (str (clojure.string/join "" (repeat (- 7 (count istr)) "0")) istr))

(defn int-to-bin-string [i]
    (pad-int (Integer/toString i 2)))


(defn letter-to-bin [char]
    (int char))

(defn trans-cnt [num]
    (reduce str (repeat num 0)))

(defn trans-01 [num]
    (if (= "0" (str num))
        "00"
        "0"))

(defn get-next-digit [bin]
    (let [bin-str bin
          first-digit (first (str bin-str))]
        (loop [cnt 0
               rem bin]
           (let [remstr (str rem)]

    (binding [*out* *err*]
      (println "first: " first-digit)
      (println "nd-rem: " remstr)
      (println "rem type: " (type remstr))
      (println "cnt: " cnt)
    )
            (if (or (= 0 (count remstr)) (not (= (first remstr) first-digit)))
                [(str (trans-01 first-digit) " " (trans-cnt cnt)) remstr]
                (recur (inc cnt) (reduce str (rest remstr))))))))

(defn encode [bin]
    (loop [acc ""
           rem bin]
       "0"))

(defn bins-to-str [bins]
    (loop [rem (reduce str (map str bins))
           acc []]

    (binding [*out* *err*]
      (println "^^^^^^^")
      (println "rem: " rem)
      (println "acc: " acc)
    )
        (if (= 0 (count (str rem)))
            acc
            (let [[s r2] (get-next-digit rem)
                  r2str (reduce str (map str r2))]
    (binding [*out* *err*]
      (println "r2: " r2)
      (println "r2str: " r2str)
      (println "------"))
               (recur (reduce str r2) (conj acc s))))))


(defn -main [& args]
  (let [MESSAGE (read-line)
        bins (map int-to-bin-string (map int (vec MESSAGE)))]

    (binding [*out* *err*]
      (println "message: " MESSAGE)
      (println "bins: " bins)
      (println "=====================")
    )

    ; Write answer to stdout
    (println (clojure.string/join " " (bins-to-str bins)))))

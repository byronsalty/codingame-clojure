(ns Solution
  (:gen-class))

; Auto-generated code below aims at helping you parse
; the standard input according to the problem statement.

(defn create-lookup [N]
    (loop [i N
           acc {}]
        (if (= 0 i)
            acc
            (let [EXT (read) MT (read)]
                (recur (dec i) (assoc acc (.toLowerCase (str EXT)) MT))))))

(defn get-ext [FNAME]
    (let [padded (.toLowerCase (str FNAME " "))
          splits (clojure.string/split padded #"\.")]

        (if (= 1 (count splits))
            ""
            (clojure.string/trimr (last splits)))))


(defn -main [& args]
  (let [N (read) Q (read)
        mimes (create-lookup N)]

    (binding [*out* *err*]
        (println "mimes: " mimes))

    (read-line) ; getting a single blank line for some reason

    (loop [i Q]
      (when (> i 0)
        (let [FNAME (read-line)
              guess (get mimes (get-ext FNAME) "UNKNOWN")]

            (binding [*out* *err*]
                (println "  fname: " FNAME)
                (println "  ext: " (get-ext FNAME))
                (println "  guess: " guess))
          ; FNAME: One file name per line.
            (println guess)
            (recur (dec i)))))))

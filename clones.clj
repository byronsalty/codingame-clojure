(ns Player
  (:gen-class)
  (:use [clojure.string :only [split trim]]))

; Auto-generated code below aims at helping you parse
; the standard input according to the problem statement.


(defn readn []
    (Integer. (read)))

(defn abs [num]
    (max num (* -1 num)))

(defn sign [num]
    (if (= 0 num)
        0
        (/ num (abs num))))

(defn get-elevators [num]
    (loop [i num
           acc #{}]
       (if (= 0 i)
           acc
           (let [floor (readn) pos (readn) skip (read-line)]
            (recur (dec i) (conj acc [floor pos]))))))

(defn dir-sign [dir]
            ;(binding [*out* *err*]
            ;    (println "dir: " dir)
            ;    (println "equal: " (= "RIGHT" (str dir))))
    (if (= "RIGHT" (str dir)) +1 -1))

(defn check-in-bounds [width pos dir]
    (let [sign (dir-sign dir)
          next (+ pos sign)]
        (if (= 0 (mod next width)) false true)))

(defn analyze-floor [num exitFloor exitPos elevators]
    (if (= num exitFloor)
        exitPos
        (let [el (filter (fn [x] (= num (first x))) elevators)]
            (binding [*out* *err*]
                (println "elevator: " el))
            (second (first el)))))

(defn check-wrong-dir [dir clonePos pos]
            (binding [*out* *err*]
                (println "dir/c/p: " dir " " clonePos " " pos))
    (let [needed-sign (sign (- pos clonePos))
          current-sign (dir-sign dir)]

            (binding [*out* *err*]
                (println "need: " needed-sign)
                (println "current: " current-sign))
        (not (= needed-sign current-sign))))



(defn get-command [at-pos wrong-dir]
    (if (or at-pos (not wrong-dir))
        "WAIT"
        "BLOCK"))

(def desired-dir

(defn -main [& args]
  (let [nbFloors (readn) width (readn) nbRounds (readn)
        exitFloor (readn) exitPos (readn)
        nbTotalClones (readn) nbAdditionalElevators (readn) nbElevators (readn)
        skip (read-line)
        elevators (get-elevators nbElevators)]

    (binding [*out* *err*]
        (println "floors: " nbFloors)
        (println "width: " width)
        (println "rounds: " nbRounds)
        (println "exitFloor: " exitFloor)
        (println "exitPos: " exitPos)
        (println "clones: " nbTotalClones)
        (println "add elev: " nbAdditionalElevators)
        (println "nb elev: " nbElevators)
        (println "elevators: " elevators)
    )


    (while true
      (let [cloneFloor (readn) clonePos (readn) direction (read) skip (read-line)]

        (binding [*out* *err*]
            (println "floor: " cloneFloor)
            (println "pos: " clonePos)
            (println "dir: " direction))

        (if (> cloneFloor -1)
        (let [in-bounds (check-in-bounds width clonePos direction)
            pos (analyze-floor cloneFloor exitFloor exitPos elevators)
            at-pos (= pos clonePos)
            wrong-dir (check-wrong-dir direction clonePos pos)
            command (get-command at-pos wrong-dir)]

        (binding [*out* *err*]
            (println "floor: " cloneFloor)
            (println "pos: " clonePos)
            (println "dir: " direction)
            (println "in-bounds: " in-bounds)
            (println "wrong-dir: " wrong-dir)
            (println "command: " command)
        )

        (println command))
        (println "WAIT"))
      )))))

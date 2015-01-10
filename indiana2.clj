(ns Player
  (:gen-class)
  (:use [clojure.string :only [split trim]])
  (:use [clojure.set :only [intersection union]]))

; Auto-generated code below aims at helping you parse
; the standard input according to the problem statement.

(defmacro tl [t b l r]
  (list 'fn '[x y p] (list 'cond '(= p "TOP") t
                                 '(= p "LEFT") l
                                 '(= p "RIGHT") r)))

(defmacro rot [l r]
  (list 'fn '[n dir] (list 'cond '(= dir "LEFT") l
                                 '(= dir "RIGHT") r)))

(def tiles
    {0 (tl [-1 -1] [-1 -1] [-1 -1])
     1 (tl [x (inc y)] [x (inc y)] [x (inc y)])
     2 (tl [-1 -1] [(inc x) y] [(dec x) y])
     3 (tl [x (inc y)] [-1 -1] [-1 -1])
     4 (tl [(dec x) y] [-1 -1] [x (inc y)]))
     5 (tl [(inc x) y] [x (inc y)] [-1 -1]))
     6 (tl [-1 -1] [(inc x) y] [(dec x) y])
     7 (tl [x (inc y)] [-1 -1] [x (inc y)])
     8 (tl [-1 -1] [x (inc y)] [x (inc y)])
     9 (tl [x (inc y)] [x (inc y)] [-1 -1])
     10 (tl [(dec x) y] [-1 -1] [-1 -1])
     11 (tl [(inc x) y] [-1 -1] [-1 -1])
     12 (tl [-1 -1] [-1 -1] [x (inc y)])
     13 (tl [-1 -1] [x (inc y)] [-1 -1])
    })

(def rotations
    {0 (rot 0 0)
     1 (rot 1 1)
     2 (rot 3 3)
     3 (rot 2 2)
     4 (rot 5 5)
     5 (rot 4 4)
     6 (rot 9 7)
     7 (rot 6 8)
     8 (rot 7 9)
     9 (rot 8 6)
     10 (rot 13 11)
     11 (rot 10 12)
     12 (rot 11 13)
     13 (rot 12 10)
     }

(defn readn []
    (Integer. (read)))

(defn abs [n]
    (max (* -1 n) n))


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
    (tiles (abs tile-num)))

(defn get-rocks [num]
    (loop [i num
           acc []]
        (if (= 0 i)
            acc
            (let [XR (read) YR (read) POSR (read)]
                (recur (dec i) (conj acc [XR YR POSR]))))))


(defn -main [& args]
  (let [W (readn) H (readn) skip (read-line)
        lines (get-lines W H)
        exit (readn) s2 (read-line)]

          (binding [*out* *err*]
            (println "W/H: " W "/" H)
            (println "lines: " lines)
            (println "exit: " exit))

      (while true
        (let [XI (readn) YI (readn) POS (read) ROCKS (readn) s3 (read-line)
              rocks (get-rocks ROCKS)
              tile-num (get-tile-num lines XI YI)
              tile (get-tile tile-num)
              [nextX nextY] (tile XI YI POS)]

          (binding [*out* *err*]
            (println "X, Y: " XI "," YI)
            (println "POS: " POS)
            (println "X', Y': " nextX "," nextY))

          ; One line containing the X Y coordinates of the room in which you believe Indy will be on the next turn.
          (println "WAIT")))))

          ; One line containing on of three commands: 'X Y LEFT', 'X Y RIGHT' or 'WAIT'

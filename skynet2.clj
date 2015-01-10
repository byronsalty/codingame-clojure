(ns Player
  (:gen-class)
  (:use [clojure.string :only [split trim]])
  (:use [clojure.set :only [intersection union]]))

; Auto-generated code below aims at helping you parse
; the standard input according to the problem statement.

(defn get-links [L]
    (loop [i L
           acc #{}]
        (if (= i 0)
            acc
            (recur (dec i) (conj acc (split (read-line) #"\s"))))))

(defn get-exits [E]
    (loop [i E
           acc []]
        (if (= i 0)
            acc
            (recur (dec i) (conj acc (str (read-line)))))))

(defn eq-2 [target a b]
    (or (= target a) (= target b)))

(defn get-node-links [links node]
    (set (filter (fn [lnk] (eq-2 node (first lnk) (second lnk))) links)))

(defn select-target [agent-links exit-links]
    (if (nil? (first (intersection agent-links exit-links)))
        (first exit-links)
        (first (intersection agent-links exit-links))))

(defn -main [& args]
  (let [N (read) L (Integer. (read)) E (Integer. (read))
        x (read-line)
        orig-links (get-links L)
        exits (get-exits E)]
    ; N: the total number of nodes in the level, including the gateways
    ; L: the number of links
    ; E: the number of exit gateways

    (binding [*out* *err*]
        (println "N L E: " N L E)
        (println "links: " orig-links)
        (println "exits: " exits)
        )

    (loop [links orig-links]
      (let [SI (str (read))
            agent-links (get-node-links links SI)
            exit-links (reduce union (map (fn [x] (get-node-links links x)) exits))
            target (select-target agent-links exit-links)]
        ; SI: The index of the node on which the Skynet agent is positioned this turn

        (binding [*out* *err*]
            (println "agent links: " agent-links)
            (println "exit links: " exit-links)
        )

        ; Example: 0 1 are the indices of the nodes you wish to sever the link between
        (println (first target) (second target))
        (recur (disj links target))))))

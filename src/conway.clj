(ns conway)

(defstruct cell :status)

(def live (struct cell "*"))
(def dead (struct cell ""))

(defn flip [cell]
  (if (= cell live) dead live))

(defn show [cell]
  (:status cell))



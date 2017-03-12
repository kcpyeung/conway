(ns conway)

(defstruct cell :status)

(def live (struct cell "*"))
(def dead (struct cell ""))

(defn flip [cell]
  (if (= cell live) dead live))

(defn show [cell]
  (:status cell))

(defn row [count live-coord]
  (def add-cell
    (fn [coll cnt]
      (if (= -1 cnt)
        coll
        (if (some #(= cnt %) live-coord)
          (add-cell (conj coll live) (dec cnt))
          (add-cell (conj coll dead) (dec cnt))))))
  (add-cell '() (dec count)))




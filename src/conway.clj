(ns conway)

(defstruct cell :status)

(def live (struct cell "*"))
(def dead (struct cell " "))

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

(defn board [rows live-coord]
  (def add-row
    (fn [coll cnt]
      (if (= -1 cnt)
        coll
        (add-row (conj coll (row rows (nth live-coord cnt))) (dec cnt)))))
  (add-row '() (dec rows)))

(defn print-row [row]
  (apply str (map :status row)))

(defn print-board [board]
  (map print-row board))





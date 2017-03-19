(ns conway)

(def live {:status "*"})
(def dead {:status " "})

(defn flip [cell]
  (if (= cell live) dead live))

(defn live? [cell]
  (= live cell))

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

(defn get-cell [board cell]
  (let [[x y] cell]
    (nth (nth board y) x)))

(defn- unwanted-cells? [board-size x y cell]
  (let [[x' y'] cell]
    (or
      (< x' 0)
      (< y' 0)
      (>= x' board-size)
      (>= y' board-size)
      (and (= x x') (= y y'))
     )))

(defn get-neighbour-coords [board-size cell]
  (let [[x y] cell
        neighbouring-y (range (dec y) (+ 2 y))]
    (remove #(unwanted-cells? board-size x y %)
      (concat
        (map #(vector (dec x) %) neighbouring-y)
        (map #(vector      x  %) neighbouring-y)
        (map #(vector (inc x) %) neighbouring-y))
  )))

(defn get-neighbours [board cell]
  (let [neighbour-coords (get-neighbour-coords (count board) cell)]
    (map #(get-cell board %) neighbour-coords)
  ))

(defn- step-live-cell [board cell live-neighbour-count]
  (case live-neighbour-count
    2 live
    3 live
    dead))

(defn- step-dead-cell [live-neighbour-count]
  (if (= 3 live-neighbour-count) live dead))

(defn step-cell [board cell]
  (let [current-cell (get-cell board cell)
        neighbours (get-neighbours board cell)
        live-neighbours (filter live? neighbours)
        live-neighbour-count (count live-neighbours)]
    (if (live? current-cell)
      (step-live-cell board cell live-neighbour-count)
      (step-dead-cell live-neighbour-count))))







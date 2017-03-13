(ns conway-test
  (:require
    [clojure.test :refer [deftest testing is]]
    [conway]))

(deftest test-flip-dead
  (testing "flipping a dead cell makes it live"
    (is (= conway/live (conway/flip conway/dead)))))

(deftest test-flip-live
  (testing "flipping a live cell makes it dead"
    (is (= conway/dead (conway/flip conway/live)))))

(def test-show-live-cell
  (testing "showing a live cell gets a star"
    (is (= "*" (conway/show conway/live)))))

(def test-show-dead-cell
  (testing "showing a dead cell gets a blank"
    (is (= " " (conway/show conway/dead)))))

(def test-create-row-of-cells
  (testing "creating the correct number of cells"
    (is (= 8 (count (conway/row 8 [0 3 7]))))))

(def test-create-row-of-cells
  (testing "creating specified live cells"
    (let [row (conway/row 8 [0 3 7])]
      (is (= conway/live (nth row 0)))
      (is (= conway/dead (nth row 1)))
      (is (= conway/dead (nth row 2)))
      (is (= conway/live (nth row 3)))
      (is (= conway/dead (nth row 4)))
      (is (= conway/dead (nth row 5)))
      (is (= conway/dead (nth row 6)))
      (is (= conway/live (nth row 7)))
    )))

(def test-create-board
  (testing "creating a board made up of rows of cells"
    (let [board (conway/board 8 [[] [] [] [] [] [] [] []])]
      (is (= 8 (count board))))))

(def test-print-row
  (testing "printing a row gets its characters"
    (let [row (conway/row 8 [0 3 7])]
      (is (= "*  *   *" (conway/print-row row))))))

(def test-print-board
  (testing "printing a board gets its characters"
    (let [board (conway/board 8 [[0] [1] [2] [3] [4] [5] [6] [7]])]
      (is (= '("*       "
               " *      "
               "  *     "
               "   *    "
               "    *   "
               "     *  "
               "      * "
               "       *") (conway/print-board board))))))

(def test-get-cell
  (testing "getting a cell by its coord"
    (let [board (conway/board 8 [[0] [1] [2] [3] [4] [5] [6] [7]])]
      (is (= (conway/get-cell board 0 0) conway/live))
      (is (= (conway/get-cell board 0 1) conway/dead))
      (is (= (conway/get-cell board 1 0) conway/dead))
      (is (= (conway/get-cell board 1 1) conway/live))
    )))







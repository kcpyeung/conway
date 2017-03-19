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

(deftest test-show-live-cell
  (testing "showing a live cell gets a star"
    (is (= "*" (conway/show conway/live)))))

(deftest test-show-dead-cell
  (testing "showing a dead cell gets a blank"
    (is (= " " (conway/show conway/dead)))))

(deftest test-create-row-of-cells
  (testing "creating the correct number of cells"
    (is (= 8 (count (conway/row 8 [0 3 7]))))))

(deftest test-create-row-of-cells
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

(deftest test-create-board
  (testing "creating a board made up of rows of cells"
    (let [board (conway/board 8 [[] [] [] [] [] [] [] []])]
      (is (= 8 (count board))))))

(deftest test-print-row
  (testing "printing a row gets its characters"
    (let [row (conway/row 8 [0 3 7])]
      (is (= "*  *   *" (conway/print-row row))))))

(deftest test-print-board
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

(deftest test-get-cell
  (testing "getting a cell by its coord"
    (let [board (conway/board 8 [[0] [1] [2] [3] [4] [5] [6] [7]])]
      (is (= (conway/get-cell board [0 0]) conway/live))
      (is (= (conway/get-cell board [0 1]) conway/dead))
      (is (= (conway/get-cell board [1 0]) conway/dead))
      (is (= (conway/get-cell board [1 1]) conway/live))
    )))

(deftest test-get-neighbour-coords-middles
  (testing "getting middle cells has 8 neighbours and excludes self"
    (is (= '([0 0] [0 1] [0 2] [1 0] [1 2] [2 0] [2 1] [2 2]) (conway/get-neighbour-coords 4 [1 1])))))

(deftest test-get-neighbour-coords-negatives
  (testing "getting top or left cells does not return negatives"
    (is (= '([1 0] [1 1] [2 1] [3 0] [3 1]) (conway/get-neighbour-coords 4 [2 0])))))

(deftest test-get-neighbour-coords-far-right
  (testing "getting far right cells does not go over right edge"
    (is (= '([2 0] [2 1] [3 1]) (conway/get-neighbour-coords 4 [3 0])))))

(deftest test-get-neighbour-coords-far-bottom
  (testing "getting far bottom cells does not go over bottom edge"
    (is (= '([2 2] [2 3] [3 2]) (conway/get-neighbour-coords 4 [3 3])))))

(deftest test-get-neighbours
  (testing "getting neighbours of (0,0) returns cells in (0,1), (1,0) and (1,1)"
    (let [board (conway/board 8 [[0] [1] [2] [3] [4] [5] [6] [7]])]
      (is (= (list conway/dead conway/dead conway/live) (conway/get-neighbours board [0 0]))))))

(deftest test-step-cell-under-population
  (testing "a live cell dies of under-population"
    (let [board (conway/board 8 [[0] [1] [2] [3] [4] [5] [6] [7]])
          new-cell (conway/step-cell board [0 0])]
      (is (= conway/dead new-cell)))))

(deftest test-step-cell-lives-on-2
  (testing "a live cell lives on with 2 neighbours"
    (let [board (conway/board 8 [[0] [1] [2] [3] [4] [5] [6] [7]])
          new-cell (conway/step-cell board [1 1])]
      (is (= conway/live new-cell)))))

(deftest test-step-cell-lives-on-3
  (testing "a live cell lives on with 3 neighbours"
    (let [board (conway/board 8 [[0] [1 2] [2] [3] [4] [5] [6] [7]])
          new-cell (conway/step-cell board [1 1])]
      (is (= conway/live new-cell)))))






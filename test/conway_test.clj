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
    (is (= "" (conway/show conway/dead)))))

(def test-create-row-of-cells
  (testing "creating a row of cells of 8 cells"
    (is (= 8 (count (conway/row 8 [0 3 7]))))))

(def test-create-row-of-cells
  (testing "creating a row of cells of 8 cells"
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



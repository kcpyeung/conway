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

(ns cquant.vmath.syntax.past
  (:require
   [clojure.walk :refer [postwalk]]
   [tech.v3.datatype :as dtype]
   [tablecloth.api :as tc]))

(defn past-shift [col n]
  (dtype/make-reader
   :float64
   (tc/row-count col)
   (if (>= idx n)
     (col (- idx n))
     0)))

(defn past? [expr]
  (and (vector? expr)
       (= 2 (count expr))
       (symbol (first expr))
       (int? (second expr))))

(defn convert-past-syntax [expr]
  (postwalk
    (fn [e]
      (cond
        (past? e)  (list past-shift (first e) (last e))
        :else e))
    expr))

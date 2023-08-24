(ns test.bindings
  (:require
   [cquant.vmath.syntax.past :as p]))



(p/convert-past-syntax
  '[c (+ a b)
    d (- [c 1] c)])

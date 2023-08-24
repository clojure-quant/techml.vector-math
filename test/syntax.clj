(ns test.syntax
  (:require
    [cquant.vmath.syntax :as s]
    [cquant.vmath.syntax.column :as c]
   ))

(require '[tech.v3.dataset :as tds])
(def d (tds/->dataset {:a [1.0 2.0 3.0 4.0 5.0]
                       :b [7.0 2.0 3.0 4.0 5.0]
                       :c [1.0 2.0 3.0 4.0 100.0]}))

(s/add-cols d ['x (:a d)] [:x])


(require  '[tech.v3.datatype.functional :as fun])
(macroexpand
 '
 (s/calc d [x (+ a b)
            y (+ x c)
            z [y 1]
             ])
  )

 

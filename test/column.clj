(ns test.column
  (:require
   [cquant.vmath.syntax.column :as c]))

(require '[tech.v3.dataset :as tds])
(def d (tds/->dataset {:a [1.0 2.0 3.0 4.0 5.0]
                       :b [7.0 2.0 3.0 4.0 5.0]
                       :c [1.0 2.0 3.0 4.0 100.0]}))

(c/with-col-bindings d c)

(macroexpand-1
  '(c/with-col-bindings d b))

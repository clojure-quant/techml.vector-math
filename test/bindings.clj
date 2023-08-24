(ns test.bindings
  (:require
   [cquant.vmath.syntax.bindings :as b]))



(require '[tech.v3.dataset :as tds])
(def d (tds/->dataset {:a [1.0 2.0 3.0 4.0 5.0]
                       :b [7.0 2.0 3.0 4.0 5.0]
                       :c [1.0 2.0 3.0 4.0 100.0]}))

; + is coming from tml.functional
(b/with-default-bindings
  (+ (:a d) (:b d)))

; see if it works without user defined functions
(b/with-defined-bindings
  (+ (:a d) (:b d)))


(require '[cquant.vmath.fun.timeseries :as ts])

; use different names as in the namespace,
; as we will include all functions in the default bindings
(def bindings ['diff2 ts/diff
               'log-return2 ts/log-return
               ])

(b/set-custom-bindings bindings)

; see if we can use our defined bindings
(b/with-defined-bindings
  (diff2 (:a d)))


(macroexpand '(b/with-defined-bindings
               (+ (:a d) (:b d))))

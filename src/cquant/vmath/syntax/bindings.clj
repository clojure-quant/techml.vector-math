(ns cquant.vmath.syntax.bindings
  (:refer-clojure :exclude [+ -])
  (:require
   [tech.v3.datatype.functional :as fun])
  )

(defmacro with-bindings [bindings form]
  `(let [~@bindings]
      ~form))

(def default-bindings
  ['+ fun/+
   '- fun/-])

(defmacro with-default-bindings [form]
   `(with-bindings ~default-bindings ~form)
  )

(def custom-bindings (atom []))

(defn set-custom-bindings [bindings]
  (reset! custom-bindings bindings))


(defmacro with-defined-bindings [form]
  `(with-bindings ~default-bindings
    (with-bindings ~(deref custom-bindings) ~form))

  )

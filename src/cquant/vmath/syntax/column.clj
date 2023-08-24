(ns cquant.vmath.syntax.column
  (:require
   [tech.v3.datatype.functional :as fun]
   [tablecloth.api :as tc]))

(defn col-names [d]
  (->> d tc/columns (map meta) (map :name)))

; techml dataset+vector are java types.
; we need to be sure that in the let bindings there is nowhere
; a java value. This is why we add the get expresions on each column
;https://stackoverflow.com/questions/10735701/embedding-arbitrary-objects-in-clojure-cod

(defmacro with-col-bindings [d form]
  (let [cols-kw# (col-names (eval d))
        binding-tuple# (juxt symbol (fn [kw]
                                      `(get ~d ~kw)
                                     ))
        bindings# (->> (map binding-tuple# cols-kw#)
                      (apply concat)
                      (into []))]
      `(let [~@bindings#] ~form)

    ))

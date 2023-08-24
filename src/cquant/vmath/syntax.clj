(ns cquant.vmath.syntax
  (:require
   [tablecloth.api :as tc]
   [cquant.vmath.syntax.column :as c]
   [cquant.vmath.syntax.bindings :as b]
   [cquant.vmath.syntax.past :as p]))


(defn add-cols [ds bindings symbols]
  (let [vals (->> bindings (partition 2) (map last))
        tuples (map (fn [s v] [s v]) symbols vals)]
    (reduce (fn [ds2 [s v]]
             (tc/add-column ds2 s v))
         ds tuples
         )))

(defmacro calc [ds bindings]
  (let [escaped# (into [] ; without vec this gets evaled later
                   (map (fn [[s _v]] 
                         (keyword s)) 
                        (partition 2 bindings)))
        bindings# (p/convert-past-syntax bindings)]
  `(b/with-defined-bindings
     (c/with-col-bindings ~ds
       (let [~@bindings#]
         (add-cols ~ds ~bindings# ~escaped#)))

      )
)
  )

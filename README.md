# techml.vector-math

A simple way to do vector calculations on a techml dataset.

Lets start with an example:

```
(require '[tech.v3.dataset :as tds])
(def d (tds/->dataset {:a [1.0 2.0 3.0 4.0 5.0]
                       :b [7.0 2.0 3.0 4.0 5.0]
                       :c [1.0 2.0 3.0 4.0 100.0]}))

 (require '[cquant.vmath.syntax :as s])
 (s/calc d [x (+ a b)
            y (+ x c)
            z [y 1]
             ])
```

This gets this output:

```
|  :a |  :b |    :c |   :x |    :y |   :z |
|----:|----:|------:|-----:|------:|-----:|
| 1.0 | 7.0 |   1.0 |  8.0 |   9.0 |  0.0 |
| 2.0 | 2.0 |   2.0 |  4.0 |   6.0 |  9.0 |
| 3.0 | 3.0 |   3.0 |  6.0 |   9.0 |  6.0 |
| 4.0 | 4.0 |   4.0 |  8.0 |  12.0 |  9.0 |
| 5.0 | 5.0 | 100.0 | 10.0 | 110.0 | 12.0 |
```

# how does it work?

- you are running vector based operations on a techml dataset
- columns are referred with a normal symbol (column :c is used with symbol s)
- you can do all the normal math as you would do in a let binding with scalar values
- all variables defined in the "let binding" are added as columns to the dataset
- there is a default list of vector based functions defined (currently only + and -)
- you can add your own functions easily.
- you can use the term [x 10] to get a shifted vector that is shifted by 10 rows.
  My reason for implementing this syntax is because I work on timeseries and
  I often have to refer to prior values in vector math.

# why?

- when you want to write math, you want to be sure your math is correct.
  you do not want to worry on implementation details.
- you can use this syntax to give users who know nothing of techml architecture
  a easy way to do vector based calculations.   



# You can add your own vector functions easily:

```
(require '[cquant.vmath.fun.timeseries :as ts])

; use different names as in the namespace,
; as we will include all functions in the default bindings
(def bindings ['diff2 ts/diff
               'log-return2 ts/log-return
               ])

(b/set-custom-bindings bindings)

```

Now you can use diff2 and log-return2 in your calculations.
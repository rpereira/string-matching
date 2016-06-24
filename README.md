# String Matching -- Data Structures

This library implements the following data structures, which are useful to solve
string matching problems:

* Trie (Prefix Tree)
* SuffixArray
* SuffixArrayOptimized (using 3-way radix quicksort)

_Implementation details can be found at each file in either class or method
documentation._

## Getting started

### Prerequisites

The following dependencies must be installed on your machine:

* Java
* [Ant](http://ant.apache.org/)

#### Coloring Ant output

By default, `ant` provides a colorless output. If you prefer a colored output
which is easier to parse visually, you should pass the following argument to the
`ant` command:

    $ ant -logger org.apache.tools.ant.listener.AnsiColorLogger test

Alternatively, you can just set the following in your zsh/bash profile:

    export ANT_ARGS='-logger org.apache.tools.ant.listener.AnsiColorLogger'

### Running tests

    $ ant test

### Running the examples

See the [examples directory](./examples) for usage examples of the provided data
structures.

## Acknowledgments

The [Trie implementation](./src/main/java/TrieST.java) is based on the excellent
slides on the subject [provided by the Princeton
University](http://algs4.cs.princeton.edu/lectures/52Tries.pdf).

The [Suffix Array implementation](./src/main/java/SuffixArray.java) follows the
book [Algorithms 4th
Edition](https://www.amazon.com/Algorithms-4th-Robert-Sedgewick/dp/032157351X)
and its explanations on the subject on pages 875-883.

The [optimized version of Suffix
Array](./src/main/java/SuffixArrayOptimized.java) implements a sort operator
based on the [QuickX
implementation](http://algs4.cs.princeton.edu/23quicksort/QuickX.java).

## License

Code released under the [MIT license](./LICENSE).

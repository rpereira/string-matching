# String Matching -- Data Structures

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

First, take a look at the [examples folder](./examples). These examples use as
input data files that sit in the [data folder](./data).

You can run the examples by executing the following command:

    $ ant run-trie-example

*Note: a better approach for running the examples is yet to be determined.*

## Acknowledgments

The [Trie implementation](./src/main/java/TrieST.java) is based on the excellent
slides on the subject [provided by the Princeton
University](http://algs4.cs.princeton.edu/lectures/52Tries.pdf).

The [Suffix Array implementation](./src/main/java/SuffixArray.java) follows the
book [Algorithms 4th
Edition](https://www.amazon.com/Algorithms-4th-Robert-Sedgewick/dp/032157351X)
and its explanations on the subject on pages 875-883.

## License

Code released under the [MIT license](./LICENSE).

# String Matching -- Data Structures

## Getting started

### Prerequisites

The following dependencies must be installed on your machine:

* Java
* [Ant](http://ant.apache.org/)

### Running tests

    $ ant test

Or, if you prefer a colored output which is easier to parse visually:
### Running the examples

    $ ant -logger org.apache.tools.ant.listener.AnsiColorLogger test
First, take a look at the [examples folder](./examples). These examples use as
input data files that sit in the [data folder](./data).

You can run the examples by executing the following command:

    $ ant run-trie-example

*Note: a better approach for running the examples is yet to be determined.*

## Acknowledgments

The [Trie implementation](./src/main/java/TrieST.java) is based on the excellent
slides on the subject [provided by the Princeton
University](http://algs4.cs.princeton.edu/lectures/52Tries.pdf).

## License

Code released under the [MIT license](./LICENSE).

# pivicl

A clojurescript/node port of pivi

## Usage

1. Install Leiningen
2. npm install
3. Do "lein cljsbuild once" in root dir
4. cat test.pivi | node main.js > test.svg

## REPL

To start the node repl from within nrepl, do

1. (require 'cljs.repl.node)
2. (cemerick.piggieback/cljs-repl (cljs.repl.node/repl-env))

## License

Copyright © 2015 Rasmus Buchmann, Maximilian Klein

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.

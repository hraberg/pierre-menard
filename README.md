# Pierre Menard

*Things became duplicated in Tlön; they also tend to become effaced and lose their details when they are forgotten. A classic example is the doorway which survived so long it was visited by a beggar and disappeared at his death. At times some birds, a horse, have saved the ruins of an amphitheater.*
  -- "Tlön, Uqbar, Orbis Tertius", Jorge Luis Borges

*There is no exercise of the intellect which is not, in the final analysis, useless.*
  -- "Pierre Menard, Author of the Quixote", Jorge Luis Borges

Pierre Menard is an attempt to implement Hierarchical Temporal Memory Cortical Learning Algorithms, as described in the [Numenta](http://www.numenta.com/) [whitepaper](https://www.numenta.com/htm-overview/education/HTM_CorticalLearningAlgorithms.pdf) (Version 0.2.1, September 12, 2011) in Clojure.

If it ever works, it may be used together with [Mímir](https://github.com/hraberg/mimir) and/or [Sleipnir](https://github.com/hraberg/sleipnir).

### The Quixote

All my projects are meant to eventually fit together, not necessarily as libraries, in their concrete digital form, but as explorations:

* Deuce: Emacs based editor foundation.
* Lodjur: SWT Browser based frontend for Deuce.
* Shen.java: a minimal JVM Lisp.
* Mímir: declarative functional logic programming and production system.
* Sleipnir: GPU acceleration for Mímir, potentially also a Shen.java/KLambda backend.
* Pierre Menard: predictive analysis and machine learning to augment Mímir and Deuce.

The end goal is a new form of programming environment which proactively helps you. I'm inspired by, among other things, Metafor, Inform 7, Subtext - mostly in attitude - Jonathan Edwards showed that striving for something different wasn't futile, The Programmer's Apprentice and the work at Viewpoints Research Institute. I'm less directly inspired - but still influenced and encouraged by, the current trends of renewed IDE interest after Inventing on Principle and Light Table.

But my view is that a better editor for our current family of programming languages, and that focusing too much on language design or reactive, tactile, editors misses the big picture. I want to get away from this very notion of "programming languages".

One concrete outcome of this process is hopefully that Deuce matures into a working Emacs clone in 2013. Deuce serves a two-fold purpose: 1) to learn about what still can be considered state-of-the-art when it comes to developer productivity, and 2) actually provide an alternative road ahead for Emacs under Clojure. These are conservative (not in scope) goals, but seem worth pursuing short term.


## References

[Hierarchical Temporal Memory - including HTM Cortical Learning Algorithms (Version 0.2.1)](https://www.numenta.com/htm-overview/education/HTM_CorticalLearningAlgorithms.pdf) Jeff Hawkins, et al, 2011

[Pattern Recognition by Hierarchical Temporal Memory](http://bias.csr.unibo.it/maltoni/HTM_TR_v1.0.pdf) Davide Maltoni, 2011

[pyHTM - Hierarchical Temporal Memory in Python](https://github.com/carver/pyHTM) Jason Carver, 2010-2011 (Based on version 0.2)

[The Brain Is the Screen: Deleuze and the Philosophy of Cinema](http://www.upress.umn.edu/book-division/books/the-brain-is-the-screen) Gregory Flaxman (ed.), 2000

[Philosophy and Simulation: The Emergence of Synthetic Reason](http://computationalculture.net/review/the-plane-of-obscurity-%E2%80%94-simulation-and-philosophy) Manuel De Landa, 2011

[Metafor: Visualising Stories as Code](http://web.media.mit.edu/~hugo/publications/drafts/IUI2005-metafor.4.pdf) Hugo Liu and Henery Lieberman, 2005

[Natural Language, Semantic Analysis and Interactive Fiction](http://inform7.com/learn/documents/WhitePaper.pdf) Graham Nelson, 2005, revised 2006

[Approaches to Automatic Programming](http://www.merl.com/papers/docs/TR92-04.pdf) Charles Rich and Richard C. Waters, 1992

[STEPS Toward Espressive Programming Systems](http://www.vpri.org/pdf/tr2011004_steps11.pdf) Viewpoints Research Institute, 2011

[Pierre Menard, Author of the Quixote](http://vahidnab.com/menard.pdf) Jorge Luis Borges, 1939


## License

"Use of Numenta’s software and intellectual property, including the ideas contained in [this document](https://www.numenta.com/htm-overview/education/HTM_CorticalLearningAlgorithms.pdf), are free for non-commercial research purposes.  For details, see http://www.numenta.com/about-numenta/licensing.php."

Copyright © 2012 Håkan Råberg

Distributed under the Eclipse Public License, the same as Clojure.

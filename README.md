# Pierre Menard

*There is no exercise of the intellect which is not, in the final analysis, useless.*

-- "Pierre Menard, Author of the Quixote", Jorge Luis Borges


Pierre Menard started as an attempt to implement Hierarchical Temporal Memory Cortical Learning Algorithms, as described in the [Numenta](http://www.numenta.com/) [whitepaper](https://www.numenta.com/htm-overview/education/HTM_CorticalLearningAlgorithms.pdf) (Version 0.2.1, September 12, 2011) in Clojure after seeing Jeff Hawkins speak at [Strange Loop 2012](http://www.infoq.com/presentations/Brain-Computing).

Now, this isn't what Pierre Menard is. Instead I've been spending a few weeks reading up on the background around various "cognitive" systems. To be frank, I was treading water a bit - I was bouncing back and forth between paper after paper in Answer Set Programming, Functional Logic Programming, Co-Inductive Logic Programming, and started slowly feel that I was missing the big picture.

So I needed some new inspiration, and Jeff Hawkins' keynote did set me down this road, which initially was pretty humble, just trying to understand the pseudo code in the Numenta whitepaper, but I realized I needed a better, both technical and critical, understanding of the different pieces here.

There are many interesting papers and thoughts out there, but they take time to analyze and consolidate into a working model of software. One key is to program and compose emergent behavior - I don't know exactly how to describe what I mean here, but we'll know it when we see it. Another key is to get the computer to help us on the way - to explain what its doing and why, and suggest alternatives. These two aren't necessarily dependent on each other. A third key is potentially continental philosophy - this is not so much to provide answers, but to help detect shortcuts - both to take and avoid.

As a side note - many papers talk about AI - while I still think "programming", and I'm not sure if that's because I have a too broad or optimistic view of what programming is or can be, a misappropriation of AI and machine learning (this is very likely), or if it can be attributed to arbitrary delineations between the different kinds of "computing". I'm mainly interested in efficiently make the computer do what I want, and most of the work, instead of the other way around.

And I'm finally starting to see overlap between the references in De Landa "Philosophy and Simulation" (2011) and papers I currently read, but arrived at from a different angle. There's a pattern here somewhere, and, to paraphrase Jeff: "I have a feeling its going to be important". Potentially what is needed is a book describing the ideas and findings - again, taking cue from Jeff - "On Intelligence" (2004) is a book that calls for of action, not one that has all the answers.

Pierre Menard is a scratch pad. It's not a new rule engine, language or an IDE, its a creation of a concept, similar to what was and is, but also, what is yet to be. More concretely, I aim to explore an intersection of ideas around sparse distributed representation, hyper-dimensional computing, fluid analogies, quantum neural networks, cortical learning, production systems and functional logic programming applied to human style problem solving.

And "what about Deuce?" - I will keep working on Deuce, it is a totally different form of programming, it needs a different type of problem solving, more engineering and heavy lifting, and less theoretical thinking. I plan to work on both Pierre Menard and Deuce for at least 2013.

And "what about Deleuze?" - that's what I wanna know!



## References

[Hierarchical Temporal Memory - including HTM Cortical Learning Algorithms (Version 0.2.1)](https://www.numenta.com/htm-overview/education/HTM_CorticalLearningAlgorithms.pdf) Jeff Hawkins, et al, 2011

[Hyperdimensional Computing: An Introduction to Computing in Distributed Representation with High-Dimensional Random Vectors](http://redwood.berkeley.edu/pkanerva/papers/kanerva09-hyperdimensional.pdf) Pentti Kanerva, 2009

[Sparse Distributed Memory: Principles and Operation](ftp://reports.stanford.edu/pub/cstr/reports/csl/tr/89/400/CSL-TR-89-400.pdf) Pentti Kanerva et al, 1989

[Simple principles of cognitive computation with distributed representations](http://pure.ltu.se/portal/files/36779244/Blerim_Emruli_Kappa_Lic2012.pdf) Blerim Emruli, 2012

[Integer Sparse Distributed Memory and Modular Composite Representation](http://ccrg.cs.memphis.edu/assets/papers/theses-dissertations/Snaider_dissertation.pdf) Javier Snaider, 2012

[Comparison Between Kanerva's SDM and Hopfield-Type Neural Networks](http://www.google.co.uk/url?sa=t&rct=j&q=&esrc=s&source=web&cd=2&cad=rja&ved=0CCYQFjAB&url=http%3A%2F%2Fcsjarchive.cogsci.rpi.edu%2F1988v12%2Fi03%2Fp0299p0329%2Fmain.pdf&ei=TymFUJncLKW80QWDzICIBA&usg=AFQjCNH8UomRBwA1XWYwT0OpmkK42lsWlQ) James Keeler, 1988

[What Might Categories do for AI and Cognitive Science?](http://www.j-paine.org/why_be_interested_in_categories.html) Jocelyn Ireson-Paine, 2009

[How Minds Work: Tutorial](http://ccrg.cs.memphis.edu/tutorial/tutorial.html) Stan Franklin, 2008 - "How Minds Work is intended to give a detailed picture of several mechanisms of mind combined to produce a conceptual model of cognition in the context of a human-like software agent".

[Quantum Computation via Sparse Distributed Representation](http://www.neuroquantology.com/index.php/journal/article/download/507/504) Gerald Rinkus, 2012 "SDR, which entails no new, esoteric technology, is the key to achieving quantum computation in a single-processor, classical (Von Neumann) computer." - this sounds like hyperbole, but I know too little about this field, and such statements obviously at the same time peaks my interest.

[Neural Cognitive Modelling:  A Biologically Constrained Spiking Neuron Model of the Tower of Hanoi Task](http://ctnsrv.uwaterloo.ca/cnrglab/sites/ctnsrv.uwaterloo.ca.cnrglab/files/papers/Stewart.Hanoi_.pdf) Terrence C. Stewart and Chris Eliasmith, 2011

[The Copycat Project: A Model of Mental Fluidity and Analogy-making](http://cognitrn.psych.indiana.edu/rgoldsto/courses/concepts/copycat.pdf) Douglas Hofstader and Melanie Mitchell, 1995

[FAE: The Fluid Analogies Engine. A Dynamic, Hybrid Model of Perception and Mental Deliberation](http://itee.uq.edu.au/~scottb/thesis.pdf) Scott William Bolland, 2005

[The emergence of choice: Decision-making and strategic thinking through analogies](http://cogprints.org/6615/2/Capyblanca_cogprints.pdf) Alexandre Linhares, 2009

<a href="http://www.eicstes.org/EICSTES_PDF/PAPERS/The%20Self-Organizing%20Map%20(Kohonen).pdf">The Self-Organizing Map</a> Teuvo Kohonen, 1990

[Beyond Containing: Associative Storage and Memory](http://theputnamprogram.wordpress.com/2012/02/14/associative-storage-and-memory/) The "Putnam Program", 2012 - This is an interesting blog, has loads of pretty dense stuff on the future of computing, AI, Machine Learning, Architecture and Philosophy - including continental stuff like Deleuze and Foucault.

[Mindstorms](http://www.papert.org/articles/GearsOfMyChildhood.html) Seymour Papert, 1980 - link is for "The Gears of My Childhood", the foreword of Mindstorms.

[Computer Power and Human Reason: From Judgment To Calculation](http://en.wikipedia.org/wiki/Computer_Power_and_Human_Reason) Joseph Weizenbaum, 1976

[What Is Philosophy?](http://plato.stanford.edu/entries/deleuze/#Wha) Gilles Deleuze and Felix Guattari 1994

[The Brain Is the Screen: Deleuze and the Philosophy of Cinema](http://www.upress.umn.edu/book-division/books/the-brain-is-the-screen) Gregory Flaxman (ed.), 2000

[Philosophy and Simulation: The Emergence of Synthetic Reason](http://computationalculture.net/review/the-plane-of-obscurity-%E2%80%94-simulation-and-philosophy) Manuel De Landa, 2011

[Deleuze and Computers](http://www.youtube.com/watch?v=fBZPJNoJWHk) Alexander R. Galloway, 2011

[Manuel De Landa - The Philosophy of Gilles Deleuze](http://www.youtube.com/watch?v=zqisvKSuA70) Manuel De Landa, 2007

[Metafor: Visualising Stories as Code](http://web.media.mit.edu/~hugo/publications/drafts/IUI2005-metafor.4.pdf) Hugo Liu and Henery Lieberman, 2005

[Natural Language, Semantic Analysis and Interactive Fiction](http://inform7.com/learn/documents/WhitePaper.pdf) Graham Nelson, 2005, revised 2006

[Approaches to Automatic Programming](http://www.merl.com/papers/docs/TR92-04.pdf) Charles Rich and Richard C. Waters, 1992

[STEPS Toward Espressive Programming Systems](http://www.vpri.org/pdf/tr2011004_steps11.pdf) Viewpoints Research Institute, 2011

[Elephant 2000: A Programming Language Based on Speech Acts](http://www-formal.stanford.edu/jmc/elephant/elephant.html) John McCarthy, 1998 - I've read this paper a few times over the years, and have mixed feelings, but still keep coming back.

[Pierre Menard, Author of the Quixote](http://vahidnab.com/menard.pdf) Jorge Luis Borges, 1939

[The Sokal Affair](http://en.wikipedia.org/wiki/Sokal_affair) Wikipedia, regarding the github description joke.


## License

"Use of Numenta’s software and intellectual property, including the ideas contained in [this document](https://www.numenta.com/htm-overview/education/HTM_CorticalLearningAlgorithms.pdf), are free for non-commercial research purposes.  For details, see http://www.numenta.com/about-numenta/licensing.php."

Copyright © 2012 Håkan Råberg

Distributed under the Eclipse Public License, the same as Clojure.

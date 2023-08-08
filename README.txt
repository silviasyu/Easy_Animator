cs5004.animator package
1) EasyAnimator class: 
	This is the class that has the main() method to run this program. The main() 
	method takes in pairs of string arguments needed to run this program. These 
	command-line arguments are in the form -in "name-of-animation-file" 
	-view "type-of-view" -out "name-of-output-file" -speed "integer-ticks-per-second", 
	but the pairs of arguments may be in any order. Note that a view and input file 
	are required to run the program. If the view is text or svg the default output is 
	System.out and the default speed is 1, so if no outpur or speed is specified then 
	they will be set to the default. The speed has to be greater than 0. The view 
	has to be text, visual, playback, or svg. The output is only the name of the output 
	file, the extension is added according to the view, e.g. ".txt" for the text view and
	".svg" for the svg view. Note that the speed has no effect the text view unlike the 
	other three views.


cs.5004.animator.controller package
1) IController interface:
	This is the interface for the controller of this program. 
2) Controller class:
	This class is the implementation of the IController interface. It deals with concept
	of time that's in the animation. So it implements an ActionListener to listens to 
	timer events as well as button click events. The timer will create an action event 
	for every tick. That tick is thought of as the current time. So when that happens, 
	a new ArrayList<Shape> of shapes for that tick is created so that the GuiView and 
	PlaybackView can appropriately display what the shapes should look like at the tick. 
	The attributes of that shape is calculated by tweening or updated according to the 
	list of transformations.  


cs.5004.animator.model package
1) IModel interface:
	This is the interface for the model of this program.
2) Model class 
	This class is the implementation of the IModel interface. Each model object 
	contains an ArrayList<Shape> of all the original shapes in the animation and an 
	ArrayList<ShapeAnimation> of all the transformations in the animation. The shapes 
	in the ArrayList are ordered in the way that they are added to list. The 
	transformations in the ArrayList are sorted by the start time of the transformation.
 	If two transformation have the same start time, then the transfromation that is 
	added to the list first, will be first followed by the one added later.
	Model objects also store: 
	- the canvas attributes (top corner, width, height)
	- the speed, in ticks per second, of the animation
	- the end time of the animation which is the end time of the last transformation 
	  in the whole animation
	- the start time of the animation which is the appear time of the first shape that
	  appears in the animation.
	Note that the addShapeAnimation() method which adds a ShapeAnimation object to the 
	ArrayList<ShapeAnimation> of transformation also updates the disappear time of each 
	shape in the animation.


cs.5004.animator.util package
This package contains all of the interfaces, abstract and concrete classes used 
to describe and create shapes or connects the input with the model.
The following objects are used to describe and create shapes:  
1) Shape interface
	This is the interface for shapes. There are currently two shape concrete classes, 
	rectangle and oval. 
2) AbstractShape abstract class
	This is an abstract class that represents an abstract shape. It implements all the 
	methods with similar implementation among the shape concrete classes. 
	All Shape have:
	- a unique name, as a String 
	- an appear time, as an int, which is the time it appears in the animation
	- a reference point, as a Point2D object, which is the bottom left corner of a 
	  rectangle or the center of the oval
	- a width, as an int, which is the width of the rectangle or x radius of the oval
	- a height, as an int, which is the height of the rectangle or y radius of the oval
	- a color, as a RgbValue object
	All shapes have rgb values between 0 and 255 inclusively, and time, width and height 
	greater than 0 these are all checked when reading from the input file in the 
	AnimationBuilderImpl class. 
3) Rectangle concrete class
	This is a concrete class of the Shape interface and extend the AbstractShape abstract
	class. It represents a rectangle shape. 
4) Oval concrete class
	This is a concrete class of the Shape interface and extend the AbstractShape abstract
	class. It represents a oval shape. 

The following objects are used to describe and create the shape transformations
1) ShapeAnimation interface
	This is the interface for shape transformations.
2) AbstractShapeAnimation abstract class
	This is an abstract class that represents an abstract shape transformation. It implements
	all the methods with similar implementation among the ShapeAnimation concrete class.
	All ShapeAnimation have: 
	- the shape to be changed, as a Shape object, this contains all the attributes of the 
	  shape before the transformation
	- the start time of the transformation, as an int
	- the end time of the transformation, as an int
	Note that the start and end time have to be great that 0 and the start time has to be less 
	than the end time. These conditions are checks when reading from the input file in the 
	AnimationBuilderImpl class. 
4) ChangeColor 
	This is a concrete class of the ShapeAnimation interface and extends the 
	AbstractShapeAnimation abstract class. It represents a change in the color of the shape.
	All ChangeColor objects have:
	- all the parameters from the AbstractShapeAnimation abstract class
	- the new red intensity value, as an int
	- the new green intensity value, as an int
	- the new blue intensity value, as an int
5) Move
	This is a concrete class of the ShapeAnimation interface and extends the 
	AbstractShapeAnimation abstract class. It represents a change in the reference point of 
	the shape which is equivalent to moving the shape.
	All ChangeColor objects have:
	- all the parameters from the AbstractShapeAnimation abstract class
	- the new x coordinate of the reference point, as an int
	- the new y coordinate of the reference point, as an int
6) Scale
	This is a concrete class of the ShapeAnimation interface and extends the 
	AbstractShapeAnimation abstract class. It represents a change in the dimensions of the 
	shape which could be changing the width, height, or both.
	All ChangeColor objects have:
	- all the parameters from the AbstractShapeAnimation abstract class
	- the new width, as an int
	- the new height, as an int

The following two classes are to describe attributes of shape
1) Point2D class 
	This class represents the reference point of the shape.
2) RGBValue class 
	This class represents the red, green, blue intensity values of the color of the shape.

The following objects are uesd to read from input files
1) AnimationBuilder interface 
	This interface has all the operations needed to help connect the input files with model. 
2) AnimationBuilderImpl concrete class 
	This is a concrete class of the AnimationBuilder interface. 
	All AnimationBuilderImpl objects have:
	- a model,as a IModel object, which is the model that's passed as a parameter
	- a HashMap<String,String> of all the shapes declared in the input file
	- a HashMap<String,shape> of all the shapes that are already added to the animation, this 
	  is used to keep track of which shapes are in the animation so we don't accidentally add 
	  the same shape twice.
	While adding shapes and transformations to the ArrayLists in the model based on the input
	file, the addMotion() method in this class also checks if parameters passed for the shapes
	and transformations are valid. If they are not valid then a message will pop up and the 
	program terminates.
	The conditions of the parameters are: 
	- All times (appear time of the shape and start and end time of the transformation) have 
	  to be greater or equal to 0. Additionally, the start time of the transformation must be
	  less than the end time.
	- The width or x-radius has to be greater than 0.
	- The height or y-radius has to be greater than 0.
	- The RGB values must be between 0 and 255 inclusively. 
3) AnimationReader class 
	This class helps read the supplied animation input file.


cs.5004.animator.view package
1) IView interface
	This is the interface for the view of this program. There are currently four 	
	concrete classes that implement this interface: GuiView, PlaybackView, SvgView
	TextView.
2) AnimationPanel class
	This class implements JPanel. It is the main panel that shows the animation.
	It has the same dimensions as the canvas and is instantiated in the GuiView 
	and PlaybackView classes. 
The following classes are all concrete classes of the IView interface. These views show the
animation for tick = 0 even if the first shape appears at a later tick.
3) GuiView class
	This class implements JFrame and it is the GUI visual view of the animation. 
	It is a simple GUI that only has one panel where the animation is shown.
4) PlaybackView class
	This class is similar to the GuiView except it has more that just the AnimationPanel
	that shows the animation. There is also a button panel, seven JButtons, and a 
	TextField. This view supports playback controls such as start, pause, restart, 
	setting the tick or speed with the inputed speed in the TextField, enabling and 
	disabling repeating the aniamtion. 
5) SvgView class
	This class creates the svg file of the animation. After the animation finishes, the shapes
	disappear, and the the animation starts from the beginning again.
	All SvgView objects have:
	- an Appendable that is used to create the output file or print to the console
	- the width of the canvas, as an int
	- the height of the canvas, as an int
	- the top corner of the canvas, as a Point2D object
	- the end time of the animation, as an int
6) TextView class
	This class creates the textual view of the animation. It lists all the shapes with their 
	initial attributes, followed by all the summary of the shapes (the appear and disappear 
	time), and then all the transformations in the shape. Note that shapes and transformation
	appears in the order specified in the Model class. Note that the colors are printed as the 
	rgb value,(R,G,B),instead of the color name. 
	All TextView objects have:
	- an Appendable that is used to create the output file or print to the console
	- the end time of the animation, as an int















<div ng-controller="imageNotesController">
		<h1 style="text-align: center;">jQuery imgNotes - Basic Interactive Example</h1>
		<p>
			This example demonstrates a no frills note editor with a button to toggle the widget mode between view
			and edit and a button to export the notes to a table below the image.
		</p>

		<div id="imgdiv" style="text-align: center">
			<img id="image" src="/assets/img/test_image.jpg" style="border: 30px solid #ccc; padding:20px;" width="80%"/>
			<br/>
			<button id="toggleEdit">Edit</button>	<button id="export">Export</button>

		</div>
		<div id=txt></div>
</div>
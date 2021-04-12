<?php

$items = json_decode('[
	{
	  "title": "Getting started",
	  "reset_lesson_position": false,
	  "lessons": [{
		  "name": "Welcome"
		},
		{
		  "name": "Installation"
		}
	  ]
	},
	{
	  "title": "Basic operator",
	  "reset_lesson_position": false,
	  "lessons": [{
		  "name": "Addition / Subtraction"
		},
		{
		  "name": "Multiplication / Division"
		}
	  ]
	},

	{
	  "title": "Advanced topics",
	  "reset_lesson_position": true,
	  "lessons": [{
		  "name": "Mutability"
		},
		{
		  "name": "Immutability"
		}
	  ]
	}
  ]');

$section_counter = 1;
$lesson_counter  = 1;

foreach ( $items as $key => $item ) {

	if ( $item->reset_lesson_position ) {
		$lesson_counter = 1;
	}

	$item->position = $section_counter;
	$section_counter++;

	foreach ( $item->lessons as $key => $lesson ) {

		$lesson->position = $lesson_counter;
		$lesson_counter++;
	}
}

echo json_encode( $items );

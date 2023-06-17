<?php

	
	// ♦ The  "conn" Variable
	//  	→ for "Connecting" with "localhost"
	$conn = mysqli_connect(
					"localhost",	// • Host Name
					"root", 		// • Host User
					"",  			// • Password
					"quiz_db"		// • Databse Name
				); 
				
	
	
	// ♦ The  "stmt" ("statement") Variable
	// 		→ in which we Fetch 
	//		→ all the Columns of the Table:
	$stmt = $conn->prepare("SELECT 
								*
							FROM 
								math_quiz");	
	
	
	
	// ♦ "Query Execution":
	$stmt->execute();
	
	
	// ♦ "Binding" the "Results" 
	//		→ to the "Query"
	//		→ by Creating "Variables" 
	//		→ with the "Names" of the "Table Columns":
	$stmt -> bind_result(							
							$id,
							$question,
							$option1,
							$option2,
							$option3,
							$option4,
							$correct_option
						);
						
						
	// ♦ Creating "Empty Array":
	$questions_array = array();	
	
	
	// ♦ "Traversing" through "All" the "Questions"
	//		→ by using the "while()" Loop
	//		→ for "Fetching All" the "Data"
	//		→ from the "$stmt"
	//		→ and "Storing" them in an "temp" Array
	//		→ which is "Composed" of the "Key - Value" Pair:
	while($stmt -> fetch()){
		// ♦ "Array":
		$temp = array();
		
		// ♦ For "Each Index Key" of the "Array" 
		//		→ We "Store" the "Corresponding Variable":	
		$temp['id'] = $id;
		$temp['question'] = $question;
		$temp['option1'] = $option1;
		$temp['option2'] = $option2;
		$temp['option3'] = $option3;
		$temp['option4'] = $option4;
		$temp['correct_option'] = $correct_option;
		
		// ♦ "Pushing All" the Data 
		//		→ from the "$temp" Array 
		//		→ into the "$questions_array" Empty Array:
		array_push($questions_array, $temp);
	};
	
	
	
	// ♦ Displaying the "Array Result" 
	// 		→ in "JSON" Format:
	echo json_encode($questions_array);

?>


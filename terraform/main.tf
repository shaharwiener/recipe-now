provider "aws" {
  region = "us-east-1" # Change to your desired region
}

# Bucket for the application's file
resource "aws_s3_bucket" "recipe_bucket" {
  bucket = "recipe-367719679"
}

# Upload of the application file to the bucket
resource "aws_s3_bucket_object" "lambda_jar" {
  bucket = aws_s3_bucket.recipe_bucket.bucket
  key    = "recipe-now-0.0.1-SNAPSHOT"
  source = "../target/recipe-now-0.0.1-SNAPSHOT.jar"
}

# Lambda for the application
resource "aws_lambda_function" "recipe_lambda" {
  function_name = "recipe_lambda"
  handler       = "com.example.MyHandler" # Change to your handler
  runtime       = "java22" # Currently using Java version 22
  s3_bucket     = aws_s3_bucket.recipe_bucket.bucket
  s3_key        = aws_s3_bucket_object.lambda_jar.key

  environment {
    # Any environment variables can be defined here
  }

  role = aws_iam_role.lambda_exec.arn # Ensure you have an IAM role defined
}

resource "aws_iam_role" "lambda_exec" {
  name = "lambda_exec_role"

  assume_role_policy = jsonencode({
    Version = "2012-10-17"
    Statement = [
      {
        Action    = "sts:AssumeRole"
        Principal = {
          Service = "lambda.amazonaws.com"
        }
        Effect    = "Allow"
        Sid       = ""
      },
    ]
  })
}

resource "aws_iam_policy_attachment" "lambda_policy_attach" {
  name       = "lambda_policy_attach"
  roles      = [aws_iam_role.lambda_exec.name]
  policy_arn = "arn:aws:iam::aws:policy/service-role/AWSLambdaBasicExecutionRole" # Attach basic execution policy
}

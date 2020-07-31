# Deploying to App Engine
This repo is an example of how to deploy a SpringBoot/MySQL application to GCP App Engine using Cloud Build.

## Architecture

## Steps

### Set up the environment and create app engine

````
export PROJJECT=[PROJECT-NAME]
export ZONE=europe-west1-b
export REGION=europe-west1

gcloud app create
gcloud auth application-default login
````

Enabele all APIs:

````
gcloud services enable appengine.googleapis.com --async
gcloud services enable appengineflex.googleapis.com --async
gcloud services enable sqladmin.googleapis.com --async
````

### Set up Cloud SQL
In this example we will be using the MySQL version of Cloud SQL.

1. Enable Cloud SQL API
> How to do this through api?

````
gcloud sql instances create [DATABASE-NAME] --tier=db-n1-standard-1 --region=${REGION}

# Set password for user
gcloud sql users set-password root \
    --host=% --instance [DATABASE-NAME] \
    --password [PASSWORD]

# Craete database
gcloud sql databases create inventory \
    --instance=[DATABASE-NAME]

````

Get the connection name using:
````
gcloud sql instances describe [DATABASE-NAME] | grep connectionName
````
Paste the connection name in resources/application-mysql.properties, update the password and database-name properties.

Use `mvn spring-boot:run` from the terminal to test the application.

### Set up Cloud Builder

Navigate to Cloud Build -> Triggers -> Connect Repository. Connect your GitHub repo which holds this repo (Skip creating
the first trigger).

Navigate to the repo folder through Cloud Shell and use the below api:

````
    curl -X POST \
        https://cloudbuild.googleapis.com/v1/projects/${PROJECT}/triggers \
        -H "Content-Type: application/json" \
        -H "Authorization: Bearer $(gcloud auth application-default print-access-token)" \
        --data-binary @buildtrigger.json
````

### Set up App Engine
Change the `<deploy.projectId>` in the pom.xm file to point to your GCP project.

We need to grant some more permissions to the cloudbuild service account. Navigate to IAM & Admin page and use the edit
(pencil button) to add these accounts to the `cloud build service account`.
- App Engine Admin
- Cloud SQL Admin
- Secret Manager Admin

### Test
Push some code to the master branch of your github. This should activate the trigger and deploy to App Engine.


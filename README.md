# CS509-SnippetSystem
This Project was created for CS509 - Design of Software Systems. For this project, I was responsible for working on the back-end team. I wrote and tested our lambda functions including RequestHandler, Response, and Request classes for various lambdas in the snippet system. A video demo of this project can be found on my personal website. 

## Folder Structure
This project is a multi-component project. The two components are the front-end and the back-end.
The front-end is a React Project whereas the back-end is an AWS Lambda project. In order to 
work on the project, follow the below steps:

### Initial Setup
1. First things first, clone the Git Repository to your computer.
2. Next, ensure that both Webstorm and Eclipse are installed
3. With Eclipse installed, install the AWS Toolchain.
4. Additionally, install Node on to your computer. Tested with version 12.19.0: [Download link](https://nodejs.org/en/download/)

Below is the steps to set up the back-end and front-end development:

#### Back-End
1. Open Eclipse and click File > Open Projects from File System
2. Navigate to the location where you cloned the git repository and click on the folder named CS509-SnippetSystem
3. Click ok and wait for it to load. It should show two options for importing into Eclipse:
    1. CS509-SnippetSystem
    2. CS509-SnippetSystem/back-end
4. Uncheck the first option and click OK. This will load the eclipse project directory with git functionality still enabled.

You are ready to code the back-end.

#### Front-End
1. Open Webstorm and click Open.
2. Navigate to the location where you cloned the git repository and click on it. Click ok.
    1. This will open both the back-end and front-end components.
3. Expand the front-end folder.
4. In the top right, click `Add Configuration`. Add the following configuration:
    1. `install`
        1. Select the plus button in the top left and click `npm`
        2. Name it `npm install`. Next click on the dropdown for the package.json field and select the entry that appears.
        3. In the command dropdown, look for an entry called `install` and select it.
        4. Click `Apply`.
    2. `run`
        1. Select the plus button in the top left and click `npm`
        2. Name it `npm start`. Next click on the dropdown for the package.json field and select the entry that appears.
        3. In the command dropdown, ensure that the entry says `run`.
        3. Under scripts, select the `start` script.
        4. Click `Apply` and then `Ok`
5. In the top right, click the Run Configurations box (it will either show `npm install` or `npm start`) and select `npm install`
6. Run `npm install` and allow it to install on the dependencies.
7. After that finishes, run `npm start` and wait for the development server to completely start. Your default browser window will appear with the address localhost:3000.

When the `npm start` command finishes, you are ready to make realtime changes to the front end.

## Credits
Created by Khalid Alnuaim, Kevin Bimonte, Raysa Rivera-Bergollo, and Allison Silvia

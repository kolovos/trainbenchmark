#!/bin/bash

set -e # exit with nonzero exit code if anything fails

# clear and re-create the deployed-content directory
rm -rf deployed-content || exit 0
mkdir -p deployed-content/models

# copy model files (*.*), but not directories
cp -r site/* deployed-content
cp -r models/* deployed-content/models

# go to the out directory and create a *new* Git repo
cd deployed-content
git init

# inside this git repo we'll pretend to be a new user
git config user.name "FTSRG BME"
git config user.email "ftsrg.bme@gmail.com"

# The first and only commit to this new Git repo contains all the
# files present with the commit message "Deploy to GitHub Pages".
git add .
git commit -m "Deploy to GitHub Pages"

# Force push from the current repo's master branch to the remote
# repo's gh-pages branch. (All previous history on the gh-pages branch
# will be lost, since we are overwriting it.) We redirect any output to
# /dev/null to hide any sensitive credential data that might otherwise be exposed.
git push --force --quiet "https://${GH_TOKEN}@${GH_REF}" master:gh-pages > /dev/null 2>&1
